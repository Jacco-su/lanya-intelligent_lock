package com.dream.brick.equipment.action;

import com.dream.brick.admin.bean.User;
import com.dream.brick.equipment.bean.*;
import com.dream.brick.equipment.dao.*;
import com.dream.brick.listener.BasicData;
import com.dream.brick.listener.SessionData;
import com.dream.framework.dao.Pager;
import com.dream.util.FormatDate;
import com.dream.util.PropertyNote;
import com.dream.util.StringUtils;
import com.dream.util.excelTools.ExcelUtil;
import com.dream.util.excelTools.ExcelUtils;
import com.dream.util.excelTools.JsGridReportBase;
import com.dream.util.excelTools.TableData;
import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Excel controller
 */
@Controller
@RequestMapping("excel")
public class ExcelAction extends ExcelUtil{
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private CompanyDao companyDao;
    @Resource
    private ProjectDao projectDao;
    @Resource
    private ProjflowDao projflowDao;
    @Resource
    private JinzhanDao jinzhanDao;
    @Resource
    private CompanyDao compDao;
    @Resource
    private ZhengShouDao zhengShouDao;
    @Resource
    private CheckDetailDao checkDetailDao;
    @Resource
    private FundDetailsDao fundDetailsDao;
    @Resource
    private XccheckDao xccheckDao;
    @Resource
    private ProductDao productDao;
    @Resource
    private FanhuanDao fanhuanDao;
    @Autowired
    private HttpServletRequest request;
    private int P_INDEX=1;
    public ExcelAction(){
        //dataMap.put("",userService.getAll());
    }
    /**
     * 导入excel跳转
     *
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importExcel(Model model) {
        return "excel/import";
    }



    /**
     * 导出excel预览
     *
     * @param uploadExcel
     * @param
     * @return
     */
    @RequestMapping(value = "import/{clazz}", method = RequestMethod.POST)
    @ResponseBody
    public String usersImportShow(@RequestParam MultipartFile uploadExcel,
                                  @PathVariable String clazz, Model model) {
        String filePath = "";
        if (!uploadExcel.isEmpty()) {
            try {
                String path = "uploads/excel/" + System.currentTimeMillis() + (int) (Math.random() * 100) + "." + StringUtils.getFileSuffix(uploadExcel.getOriginalFilename());
                // 文件保存路径
                filePath = request.getSession().getServletContext().getRealPath("/")
                        + path;
                // 转存文件
                uploadExcel.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        // 获取前台excel的输入流
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setStartReadPos(1);
        List<Row> rowList = null;
        try {
            rowList = excelUtil.readExcel(filePath);
            saveExcel(rowList,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        model.addAttribute("list",rowList);
        model.addAttribute("clazz", clazz);
        return "success";
    }
    /**
     * 导出excel，自定义导出excel
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("export/exportExcel")
    public String exportExcel(HttpServletRequest request,
                            HttpServletResponse response,
                            String title,
                            String hearder,
                            String field, String clazz,String queryParams) throws Exception {
        response.setContentType("application/msexcel;charset=UTF-8");
        List list=getData(clazz,queryParams);
        String[] hearders =null;//表头数组
        String[] fields = null;//对象属性数组
        if(clazz.equals("Company")){
            hearders=new String[6+P_INDEX*4+1];
            fields=new String[6+P_INDEX*4+1];
            hearders[0]="企业名称";
            hearders[1]="法人代表";
            hearders[2]="联系电话";
            hearders[3]="联系人";
            hearders[4]="联系电话";
            hearders[5]="企业地址";
            hearders[6]="邮政编码";
            /*fields[0]="name";
            fields[1]="frdb";
            fields[2]="fr_phone";
            fields[3]="lxren";
            fields[4]="lxr_phone";
            fields[5]="address";
            fields[6]="yzcode";*/
            for (int i = 0; i < P_INDEX; i++) {
                hearders[6+i*4+1]="产品名称";
                hearders[6+i*4+2]="执行标准";
                hearders[6+i*4+3]="确认证书编号";
                hearders[6+i*4+4]="有效期";

                /*fields[6+i*4+1]="productName";
                fields[6+i*4+2]="zxbz";
                fields[6+i*4+3]="qrzsbh";
                fields[6+i*4+4]="validBeginTime";*/
            }
        }else{
            if(StringUtils.isNotEmpty(hearder)&&StringUtils.isNotEmpty(field)){
                hearders=hearder.split(",");
                fields=field.split(",");
            }
        }
       /* if(StringUtils.isEmpty(field)||StringUtils.isEmpty(hearder)){
            Class forName = Class.forName("com.dream.brick.equipment.bean."+clazz+"");
            title=getClazzNote(forName);
            hearders=getHearderNames(forName);
            fields=getFiledNames(forName);
        }*/

        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, SessionData.getLoginAdmin(request).getUsername(), td);
        return null;
    }

  /* //*
    @RequestMapping(value = "import/{pack}/{clazz}", method = RequestMethod.GET)
    public String usersImport(@PathVariable String pack, @PathVariable String clazz, Model model) throws ClassNotFoundException {
        Class forName = Class.forName("com.dream.brick."+pack+".bean."+clazz+"");
        model.addAttribute("map", getFiledName(forName));//获取自定义属性值
        model.addAttribute("clazz", clazz);//类
        model.addAttribute("clazzNote", getClazzNote(forName));//excel表名
        return "excel/import";
    }*/
    /**
     * 导入excel跳转
     * @param clazz->需要导入excel对应的实体名
     * @param pack->需要导入excel对应的上层包名
     * @param queryParams 查询参数
     * @return 自定义属性
     */
    @RequestMapping(value = "{type}/{pack}/{clazz}", method = RequestMethod.GET)
    public String reportForm(@PathVariable String type,
                             @PathVariable String pack,
                             @PathVariable String clazz,
                             String  queryParams,
                             Model model) throws ClassNotFoundException {
       /**
        * @author       陶乐乐(wangyiqianyi@qq.com)
        * @Description: 导出excel,导入excel跳转
        * @date         2016-03-26 11:50:08
        * @params       [type:import/export,pack:包, clazz:类, model]
        * @return       java.lang.String
        * @throws       ClassNotFoundException
        */
        model.addAttribute("clazz", clazz);
        if(StringUtils.isNotEmpty(clazz)){
            if (clazz.indexOf("Projflow")>-1) {
                clazz="Projflow";
            }
        }
        Class forName = Class.forName("com.dream.brick."+pack+".bean."+clazz+"");
        model.addAttribute("map", getFiledName(forName));
        model.addAttribute("clazzNote",getClazzNote(forName));
        model.addAttribute("queryParams",queryParams);
        return "excel/"+type;
    }
    private Map getFiledName(Class<?> clazz) {
        /**
         * @author       陶乐乐(wangyiqianyi@qq.com)
         * @Description: 获取自定义注解属性 指向实体自定义属性
         * @date         2016-03-26 12:48:30
         * @params       [clazz]
         * @return       java.util.Map
         * @throws
         */
        Map map = new LinkedHashMap();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0,len=fields.length; i <len ; i++) {
            PropertyNote propertyNote = fields[i].getAnnotation(PropertyNote.class);
            if (propertyNote != null) map.put(fields[i].getName(), propertyNote.name());
        }
        return map;
    }

    private String[] getHearderNames(Class<?> clazz) {
        /**
         * @author       陶乐乐(wangyiqianyi@qq.com)
         * @Description: 获取自定义注解属性 指向实体自定义属性
         * @date         2016-03-26 12:48:30
         * @params       [clazz]
         * @return       java.util.Map
         * @throws
         */
        Field[] fields = clazz.getDeclaredFields();
        String str[]=new String[fields.length];
        for (int i = 0,len=fields.length; i <len ; i++) {
            PropertyNote propertyNote = fields[i].getAnnotation(PropertyNote.class);
            if (propertyNote != null) str[i]=propertyNote.name();
        }
        return str;
    }

    private String[] getFiledNames(Class<?> clazz) {
        /**
         * @author       陶乐乐(wangyiqianyi@qq.com)
         * @Description: 获取自定义注解属性 指向实体自定义属性
         * @date         2016-03-26 12:48:30
         * @params       [clazz]
         * @return       java.util.Map
         * @throws
         */
        Field[] fields = clazz.getDeclaredFields();
        String str[]=new String[fields.length];
        for (int i = 0,len=fields.length; i <len ; i++) {
            PropertyNote propertyNote = fields[i].getAnnotation(PropertyNote.class);
            if (propertyNote != null) str[i]=fields[i].getName();
        }
        return str;
    }

    public static void main(String[] args) {
        String s=" , , , , ";
        System.out.println(s.length());
        System.out.println(s.split(",")[4]);
    }
    private String getClazzNote(Class<?> clazz) {
        /**
         * @author       陶乐乐(wangyiqianyi@qq.com)
         * @Description: 获取类自定义注解 指向excel表名
         * @date         2016-03-26 11:22:43
         * @params       [clazz]
         * @return       java.lang.String
         * @throws
         */
        String clazzNote = "excel";
        PropertyNote propertyNote = clazz.getAnnotation(PropertyNote.class);
        if (propertyNote != null) clazzNote = propertyNote.name();
        return clazzNote;
    }

    /**
     * param clazz
     * 获取数据列表
     */
    private List getData(String clazz,String queryParams) throws Exception {
        List list = new ArrayList();
        if(StringUtils.isNotEmpty(clazz)){
        Map<String, String> params=new HashMap<>();
        String areacode= SessionData.getAreacode(request);
        params.put("areacode", areacode);
         if (clazz.equals("Projflow")){
             list = projflowDao.findAllProjFlow("1");    //获取数据
         }else if(clazz.equals("ProjflowComp")) {
             list = projflowDao.findAllProjFlow("2");    //获取数据
         }else if(clazz.equals("ProjflowPro")) {
             list = projflowDao.findAllProjFlow("3");    //获取数据
         }
        if (clazz.equals("Company")) list = searchCompany(params);//获取数据
        if (clazz.equals("Project")) list = projectDao.findAllProject();//获取数据

       // if (clazz.equals("Jinzhan")) list = jinzhanDao.findList(" from Jinzhan");

        if(clazz.equals("ZhengShou"))list=zhengShouDao.serarch(params);

        if(clazz.equals("Xccheck"))list=xccheckDao.serarch(params);
        if(clazz.equals("CheckDetail")){
            String  pstr [];
            pstr=queryParams.split(",");
            params.put("danganbx",pstr[0]);
            params.put("builder",pstr[1]);
            params.put("proj_name",pstr[2]);
            params.put("begindate",pstr[3]);
            params.put("enddate",pstr[4]);
            list=checkDetailDao.serarch(params);
        }
        if(clazz.equals("Fanhuan"))list=fanhuanDao.serarch(params);
        if(clazz.equals("FundDetails"))list= fundDetailsDao.searchFund(params);
        }
        return list;
    }

    private List saveExcel(List<Row> rowList, String clazz) throws Exception {
        if(clazz.equals("Projflow")) {
            for (int i = 0; i < rowList.size(); i++) {
                Row row = rowList.get(i);
                saveProjflowExcel(row);
            }
        }
        if(clazz.equals("ZhengShou")) {
            for (int i = 0; i < rowList.size(); i++) {
                Row row = rowList.get(i);
                saveZhengShouExcel(row);
            }
        }
        if(clazz.equals("FundDetails")) {
            for (int i = 0; i < rowList.size(); i++) {
                Row row = rowList.get(i);
                saveFundDetailsExcel(row);
            }
        }
        if(clazz.equals("Xccheck")) {
            for (int i = 0; i < rowList.size(); i++) {
                Row row = rowList.get(i);
                saveXccheckExcel(row);
            }
        }
        if(clazz.equals("CheckDetail")) {
            for (int i = 0; i < rowList.size(); i++) {
                Row row = rowList.get(i);
                saveCheckDetailExcel(row);
            }
        }
        if(clazz.equals("Fanhuan")) {
            for (int i = 0; i < rowList.size(); i++) {
                Row row = rowList.get(i);
                saveFanhuanExcel(row);
            }
        }
        return rowList;
    }
    private void saveProjflowExcel(Row row) throws Exception {
            if (StringUtils.isNotEmpty(getCellValue(row.getCell(0)))) {
                Company company=compDao.getCompanyByName(getCellValue(row.getCell(0)).trim());
                if (company != null) {
                    Projflow projflow = new Projflow();
                    User user = SessionData.getLoginAdmin(request);
                    projflow.setDjren(user.getUsername());
                    projflow.setDjrenid(user.getId());
                    projflow.setDjtime(FormatDate.getYMdHHmm());
                    projflow.setAreacode(SessionData.getAreacode(request));
                    projflow.setCompany_id(company.getId());
                    projflow.setCompname(getCellValue(row.getCell(0)));
                    projflow.setProj_name(getCellValue(row.getCell(1)));
                    projflow.setAddress(getCellValue(row.getCell(2)));
                    projflow.setAreaname(getCellValue(row.getCell(3)));
                    projflow.setBuilder(getCellValue(row.getCell(4)));
                    projflow.setSgdanwei(getCellValue(row.getCell(5)));
                    projflow.setQcname(getCellValue(row.getCell(6)));
                    projflow.setQcnum(Integer.parseInt(getCellValue(row.getCell(7))));
                    String date=getCellValue(row.getCell(8));
                    if(StringUtils.isNotEmpty(date)){
                        projflow.setGhbegin(FormatDate.formatTime(date.replace(".","-")));
                    }
                    String orderExcel=getCellValue(row.getCell(9));
                    if(StringUtils.isNotEmpty(orderExcel)){
                        projflow.setHtl("0");
                        projflow.setOrderx(orderExcel+"-"+FormatDate.getMd());
                    }else {
                        Calendar now = Calendar.getInstance();
                        Object orderx = projflowDao.queryBySql("select orderx from t_projflow t order by orderx desc limit 1");
                        int year = Integer.parseInt(String.valueOf(now.get(Calendar.YEAR)).substring(2, 4));
                        int order = year * 1000 + 0001;
                        if (orderx != null) {
                            String obj = orderx.toString();
                            if (String.valueOf(year).equals(obj.substring(2, 4))) {
                                order = year * 1000 + Integer.parseInt(obj.substring(4, 7)) + 1;
                            }
                            projflow.setOrderx("第L" + order);
                        }
                        projflow.setHtl("1");
                    }
                    projflow.setGhend(getCellValue(row.getCell(10)));

                    Map<String, String> params=new HashMap();
                    String name=getCellValue(row.getCell(6));
                    String compName=getCellValue(row.getCell(0));
                    params.put("name",name.trim());
                    params.put("compName",compName.trim());
                    Product product= productDao.searchProduct(params);
                    if (product != null) {
                        List<Projprod> projprodList = new ArrayList();
                        Projprod  projprod=new Projprod();
                        projprod.setProdunit(product.getProdunit());
                        projprod.setProjflow(projflow);
                        projprod.setProduct(product);
                        projprod.setProdname(product.getName());
                        projprod.setQrzsbh(product.getQrzsbh());
                        projprod.setGycount(Integer.parseInt(getCellValue(row.getCell(7))));
                        projprodList.add(projprod);
                        projflowDao.saveProjflow(projflow,projprodList);
                    }

                }
        }
    }
    private void saveZhengShouExcel(Row row) throws Exception {
        if (StringUtils.isNotEmpty(getCellValue(row.getCell(0)))) {
            String projectName=getCellValue(row.getCell(4)).trim();
            Project project=projectDao.getProjectByName(projectName);
            if (project == null) {
                project=new Project();
                ZhengShou zhengShou = new ZhengShou();
                User user = SessionData.getLoginAdmin(request);
                zhengShou.setDjren(user.getUsername());
                zhengShou.setDjrenid(user.getId());
                zhengShou.setDjtime(FormatDate.getYMdHHmm());
                zhengShou.setXuhao(getCellValue(row.getCell(0)).toString());
                String date=getCellValue(row.getCell(1));
                if(StringUtils.isNotEmpty(date)){
                     zhengShou.setZsdate(FormatDate.formatTime(date.replace(".", "-")));
                }else{
                    zhengShou.setZsdate(FormatDate.getYMd());
                }
                zhengShou.setBjbh(getCellValue(row.getCell(2)));

                project.setBuilder(getCellValue(row.getCell(3)));
                project.setName(getCellValue(row.getCell(4)));
                project.setAddress(getCellValue(row.getCell(5)));
                project.setDjren(user.getUsername());
                project.setDjrenid(user.getId());
                project.setDjtime(FormatDate.getYMdHHmm());
                project.setAreacode(SessionData.getAreacode(request));
                Qgorg qgorg= SessionData.getLoginQgorg(request);
                project.setAreaname(qgorg.getAreaname());
                project.setOrgid(qgorg.getId());
                project.setOrgname(qgorg.getName());
                projectDao.save(project);

                zhengShou.setBjmj(Double.parseDouble(getCellValue(row.getCell(6))));
                zhengShou.setJijin(Double.parseDouble(getCellValue(row.getCell(7))));
                zhengShou.setLxren(getCellValue(row.getCell(8)));
                zhengShou.setLxr_phone(getCellValue(row.getCell(9)).toString());
                zhengShou.setJktzspjh(getCellValue(row.getCell(10)));
                String jdDate=getCellValue(row.getCell(11));
                if(StringUtils.isNotEmpty(jdDate)){
                    zhengShou.setSrpjjdrq(FormatDate.formatTime(jdDate.replace(".", "-")));
                }
                zhengShou.setAreacode(SessionData.getAreacode(request));
                zhengShou.setSrpjpjh(getCellValue(row.getCell(12)));
                zhengShou.setCompname(project.getBuilder());
                zhengShou.setProjname(project.getName());
                zhengShou.setProject(project);
                zhengShouDao.save(zhengShou);
            }
        }
    }
    private void saveFundDetailsExcel(Row row) throws Exception {
        String str=getCellValue(row.getCell(0));
        if (StringUtils.isNotEmpty(str)&&isNumeric(str)) {
           FundDetails fundDetails=new FundDetails();
            fundDetails.setAreacode(SessionData.getAreacode(request));
            fundDetails.setDswd(getCellValue(row.getCell(1)));
            fundDetails.setKpr(getCellValue(row.getCell(2)));
            fundDetails.setSrdate(getCellValue(row.getCell(3)));
            fundDetails.setTzsph(getCellValue(row.getCell(4)));
            fundDetails.setPh(getCellValue(row.getCell(5)));
            fundDetails.setJkrname(getCellValue(row.getCell(6)));
            if (StringUtils.isNotEmpty(getCellValue(row.getCell(7)))) {
                fundDetails.setFund(Double.parseDouble(getCellValue(row.getCell(7))));
            }
            fundDetailsDao.save(fundDetails);
        }
    }
    private void saveXccheckExcel(Row row) throws Exception {
        String str=getCellValue(row.getCell(0));
        if (StringUtils.isNotEmpty(str)&&isNumeric(str)) {
            String projectName=getCellValue(row.getCell(3)).trim();
            Project project=projectDao.getProjectByName(projectName);
            User user = SessionData.getLoginAdmin(request);
            if(project==null) {
                project = new Project();
                project.setBuilder(getCellValue(row.getCell(2)));
                project.setName(getCellValue(row.getCell(3)));
                project.setAddress(getCellValue(row.getCell(4)));
                project.setDjren(user.getUsername());
                project.setDjrenid(user.getId());
                project.setDjtime(FormatDate.getYMdHHmm());
                project.setAreacode(SessionData.getAreacode(request));
                Qgorg qgorg = SessionData.getLoginQgorg(request);
                project.setAreaname(qgorg.getAreaname());
                project.setOrgid(qgorg.getId());
                project.setOrgname(qgorg.getName());
                projectDao.save(project);
            }
            Xccheck xccheck=new Xccheck();
            String date=getCellValue(row.getCell(1));
            if(StringUtils.isNotEmpty(date)){
                xccheck.setSqdate(FormatDate.formatTime(date.replace(".", "-").replace("/", "-")));
            }
            if(StringUtils.isNotEmpty(getCellValue(row.getCell(5)))){
                xccheck.setCheckmj(Double.valueOf(getCellValue(row.getCell(5))));
            }
            xccheck.setLxren(getCellValue(row.getCell(6)));
            xccheck.setCheck_user(getCellValue(row.getCell(7)));
            String checkDate=getCellValue(row.getCell(8));
            if(StringUtils.isNotEmpty(checkDate)){
                xccheck.setCheck_date(FormatDate.formatTime(checkDate.replace(".", "-")));
            }
            xccheck.setDjren(user.getUsername());
            xccheck.setDjrenid(user.getId());
            xccheck.setDjtime(FormatDate.getYMdHHmm());
            xccheck.setProject(project);
            xccheck.setProjname(getCellValue(row.getCell(3)));
            xccheckDao.save(xccheck);
        }
    }
    private void saveCheckDetailExcel(Row row) throws Exception {
        String str=getCellValue(row.getCell(0));
        if (StringUtils.isNotEmpty(str)&&isNumeric(str)) {
            CheckDetail checkDetail1=checkDetailDao.getCdh(getCellValue(row.getCell(0)).trim());
            if(checkDetail1!=null){
                return;
            }
            String projectName=getCellValue(row.getCell(2)).trim();
            Project project=projectDao.getProjectByName(projectName);
            User user = SessionData.getLoginAdmin(request);
            if(project==null) {
                project = new Project();
                project.setBuilder(getCellValue(row.getCell(1)));
                project.setName(getCellValue(row.getCell(2)));
                project.setAddress(getCellValue(row.getCell(3)));
                project.setDjren(user.getUsername());
                project.setDjrenid(user.getId());
                project.setDjtime(FormatDate.getYMdHHmm());
                project.setAreacode(SessionData.getAreacode(request));
                Qgorg qgorg = SessionData.getLoginQgorg(request);
                project.setAreaname(qgorg.getAreaname());
                project.setOrgid(qgorg.getId());
                project.setOrgname(qgorg.getName());
                projectDao.save(project);
            }
                CheckDetail checkDetail = new CheckDetail();
                checkDetail.setDanganbx(getCellValue(row.getCell(0)));
                checkDetail.setHcqk(getCellValue(row.getCell(4)));
                checkDetail.setJfbz(getCellValue(row.getCell(5)));
                checkDetail.setFenduan(getCellValue(row.getCell(6)));
                checkDetail.setLxren(getCellValue(row.getCell(7)));
                String date=getCellValue(row.getCell(8));
               if(StringUtils.isNotEmpty(date)){
                   date=date.replace(".", "-").replace("/", "-");
                   checkDetail.setJfdate(FormatDate.formatTime(date));
               }
               double mj=0;
                if(StringUtils.isNotEmpty(getCellValue(row.getCell(9)))) {
                    mj=Double.parseDouble(getCellValue(row.getCell(9)));
                    checkDetail.setJzhumj(mj);
                }
                if(StringUtils.isNotEmpty(getCellValue(row.getCell(10)))) {
                    String money=getCellValue(row.getCell(10));
                    double jfMoney;
                    if(money.indexOf("SUM(J")!=-1){
                        jfMoney=mj*10;
                    }else{
                        jfMoney=Double.parseDouble(money);
                    }
                    checkDetail.setJfmoney(jfMoney);
                }
                checkDetail.setDescrip(getCellValue(row.getCell(11)));
                checkDetail.setCompname(getCellValue(row.getCell(12)));
                checkDetail.setProject(project);
                checkDetail.setFhblqk(getCellValue(row.getCell(15)));
                checkDetail.setDjren(user.getUsername());
                checkDetail.setDjrenid(user.getId());
                checkDetail.setDjtime(FormatDate.getYMdHHmm());
                checkDetailDao.save(checkDetail);
                /*if(StringUtils.isNotEmpty(getCellValue(row.getCell(12)))) {
                    Product product = productDao.searchProductQrzsbh(getCellValue(row.getCell(12)));
                    if(product!=null){
                        Detailprod detailprod = new Detailprod();
                        detailprod.setQrzsbh(getCellValue(row.getCell(12)));
                        detailprod.setCheckDetail(checkDetail);
                        if (StringUtils.isNotEmpty(getCellValue(row.getCell(13)))) {
                            detailprod.setGycount(Integer.parseInt(getCellValue(row.getCell(13))));
                        }
                        detailprod.setProduct(product);
                        checkDetailDao.saveCheckDetail(checkDetail, detailprod);
                    }
                    */

        }
    }
    private void saveFanhuanExcel(Row row) throws Exception {
        System.out.println(row.getCell(0)+":row.getCell(0)");
        String str=getCellValue(row.getCell(0));
        /*if (StringUtils.isNotEmpty(str)&&isNumeric(str)) {
            CheckDetail checkDetail=checkDetailDao.getCdh(getCellValue(row.getCell(1)).trim());
            String bili=getCellValue(row.getCell(11));
           if(checkDetail!=null){
               Fanhuan fanhuan=new Fanhuan();
               fanhuan.setCheckDetail(checkDetail);
               fanhuan.setFtbili(bili);
               fanhuan.setFttime(getCellValue(row.getCell(12)));
               fanhuan.setFtmoney(Double.parseDouble(getCellValue(row.getCell(10))));
               fanhuan.setDescrip(getCellValue(row.getCell(13)));
               fanhuanDao.save(fanhuan);
           }else{
               String projectName=getCellValue(row.getCell(3)).trim();
               Project project=projectDao.getProjectByName(projectName);
               if(project==null) {
                   project = new Project();
                   Qgorg qgorg = SessionData.getLoginQgorg(request);
                   project.setAreaname(qgorg.getAreaname());
                   project.setOrgid(qgorg.getId());
                   project.setOrgname(qgorg.getName());
                   User user = SessionData.getLoginAdmin(request);
                   project.setBuilder(getCellValue(row.getCell(2)));
                   project.setName(getCellValue(row.getCell(3)));
                   project.setAddress(getCellValue(row.getCell(4)));
                   project.setDjren(user.getUsername());
                   project.setDjrenid(user.getId());
                   project.setDjtime(FormatDate.getYMdHHmm());
                   project.setAreacode(SessionData.getAreacode(request));
                   projectDao.save(project);
               }
               checkDetail = new CheckDetail();
               checkDetail.setDanganbx(getCellValue(row.getCell(1)));
               checkDetail.setJfbz(getCellValue(row.getCell(6)));
               checkDetail.setLxren(getCellValue(row.getCell(5)));
               String date=getCellValue(row.getCell(7));
               if(StringUtils.isNotEmpty(date)){
                   date=date.replace(".", "-").replace("/", "-");
                   checkDetail.setJfdate(FormatDate.formatTime(date));
               }
               if(StringUtils.isNotEmpty(getCellValue(row.getCell(8)))) {
                   checkDetail.setJzhumj(Double.parseDouble(getCellValue(row.getCell(8))));
               }
               if(StringUtils.isNotEmpty(getCellValue(row.getCell(9)))) {
                   checkDetail.setJfmoney(Double.parseDouble(getCellValue(row.getCell(9))));
               }
               checkDetail.setCompname(getCellValue(row.getCell(11)));
               checkDetail.setProject(project);
               checkDetail.setFhblqk(getCellValue(row.getCell(14)));

               checkDetailDao.save(checkDetail);
               Fanhuan fanhuan=new Fanhuan();
               fanhuan.setCheckDetail(checkDetail);
               fanhuan.setFtbili(bili);
               fanhuan.setFttime(getCellValue(row.getCell(12)));
               fanhuan.setFtmoney(Double.parseDouble(getCellValue(row.getCell(10))));
               fanhuan.setDescrip(getCellValue(row.getCell(13)));
               fanhuanDao.save(fanhuan);
           }
        }*/
        if (StringUtils.isNotEmpty(str)&&isNumeric(str)) {
            CheckDetail checkDetail=checkDetailDao.getCdh(getCellValue(row.getCell(2)).trim());
            String bili=getCellValue(row.getCell(14));
            double jfMoney = 0;
            User user = SessionData.getLoginAdmin(request);
            Fanhuan fanhuan=new Fanhuan();
            if(checkDetail==null){
                String projectName=getCellValue(row.getCell(4)).trim();
                Project project=projectDao.getProjectByName(projectName);
                if(project==null) {
                    project = new Project();
                    Qgorg qgorg = SessionData.getLoginQgorg(request);
                    project.setAreaname(qgorg.getAreaname());
                    project.setOrgid(qgorg.getId());
                    project.setOrgname(qgorg.getName());
                    project.setBuilder(getCellValue(row.getCell(3)));
                    project.setName(getCellValue(row.getCell(4)));
                    project.setAddress(getCellValue(row.getCell(5)));
                    project.setDjren(user.getUsername());
                    project.setDjrenid(user.getId());
                    project.setDjtime(FormatDate.getYMdHHmm());
                    project.setAreacode(SessionData.getAreacode(request));
                    projectDao.save(project);
                }
                checkDetail = new CheckDetail();
                checkDetail.setDanganbx(getCellValue(row.getCell(2)));
                checkDetail.setJfbz(getCellValue(row.getCell(7)));
                checkDetail.setLxren(getCellValue(row.getCell(9)));
                String date=getCellValue(row.getCell(10));
                if(StringUtils.isNotEmpty(date)){
                    date=date.replace(".", "-").replace("/", "-");
                    checkDetail.setJfdate(FormatDate.formatTime(date));
                }
                double mj=0;
                if(StringUtils.isNotEmpty(getCellValue(row.getCell(11)))) {
                    mj=Double.parseDouble(getCellValue(row.getCell(11)));
                    checkDetail.setJzhumj(mj);
                }
                if(StringUtils.isNotEmpty(getCellValue(row.getCell(12)))) {
                    String money=getCellValue(row.getCell(12));
                    if(money.indexOf("SUM(L")!=-1){
                        jfMoney=mj*10;
                    }else{
                        jfMoney=Double.parseDouble(money);
                    }
                    checkDetail.setJfmoney(jfMoney);
                }
                checkDetail.setProject(project);
                checkDetail.setHcqk(getCellValue(row.getCell(6)));
                checkDetail.setDjren(user.getUsername());
                checkDetail.setDjrenid(user.getId());
                checkDetail.setDjtime(FormatDate.getYMdHHmm());
                checkDetailDao.save(checkDetail);

            }

            fanhuan.setProject(checkDetail.getProject());
            fanhuan.setBuilder(getCellValue(row.getCell(3)));
            fanhuan.setProjname(getCellValue(row.getCell(4)));
            fanhuan.setCheckDetail(checkDetail);
            fanhuan.setFtbili(bili);
            // fanhuan.setFttime(getCellValue(row.getCell(12)));
            fanhuan.setFtbh(getCellValue(row.getCell(1)));
            if(StringUtils.isNotEmpty(getCellValue(row.getCell(13)))) {
                fanhuan.setFtmoney(Double.parseDouble(getCellValue(row.getCell(13))));
            }
            fanhuan.setJfmoney(checkDetail.getJfmoney());
            fanhuan.setDescrip(getCellValue(row.getCell(15)));
            fanhuan.setDjren(user.getUsername());
            fanhuan.setDjrenid(user.getId());
            fanhuan.setDjtime(FormatDate.getYMdHHmm());
            fanhuanDao.save(fanhuan);
        }
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    public List<Object[]> searchCompany(Map<String, String> params) throws Exception {
        /**
         * @author       陶乐乐(wangyiqianyi@qq.com)
         * @Description: 企业信息搜索
         * @date         2016-03-29 16:30:49
         * @params       [params]
         * @return       java.util.List<com.dream.brick.equipment.bean.Company>
         * @throws       Exception
         */
        String areaCode=params.get("areacode");
        StringBuilder sb=new StringBuilder("");
        sb.append("from Company t where t.areacode  like '%").append(areaCode).append("'");
        List<Company> listCompany= companyDao.findList(sb.toString());

        StringBuilder sb2=new StringBuilder("");
        sb2.append("from Product t where t.company.areacode like '%").append(areaCode).append("'");

        List<Product> productList= productDao.findList(sb2.toString());

        List<Object[]> objs=new ArrayList<>();
        int index=0;
        for (int i = 0; i <listCompany.size() ; i++) {
            Company company=listCompany.get(i);
            List<ExtendsCompany> exCompanyList=new ArrayList<>();
            for (int j = 0; j <productList.size() ; j++) {
                Product p=productList.get(j);
                if(p.getCompany().getId().equals(company.getId())){
                    ExtendsCompany exCompany=new ExtendsCompany();
                    exCompany.setProductName(p.getName());
                    exCompany.setZxbz(p.getZxbz());
                    exCompany.setQrzsbh(p.getQrzsbh());
                    exCompany.setValidBeginTime(p.getValidBeginTime()+"至"+p.getValidEndTime());
                    exCompanyList.add(exCompany);
                }
            }
            Object[] obj=new Object[50];
            obj[0]=company.getName();
            obj[1]=company.getFrdb();
            obj[2]=company.getFr_phone();
            obj[3]=company.getLxren();
            obj[4]=company.getLxr_phone();
            obj[5]=company.getAddress();
            obj[6]=company.getYzcode();
            for (int j = 0; j <exCompanyList.size() ; j++) {
                ExtendsCompany exCompany=exCompanyList.get(j);
                obj[6+j*4+1]=exCompany.getProductName();
                obj[6+j*4+2]=exCompany.getZxbz();
                obj[6+j*4+3]=exCompany.getQrzsbh();
                obj[6+j*4+4]=exCompany.getValidBeginTime();
                index=exCompanyList.size();
                if(P_INDEX<=index){P_INDEX=index;}
            }
            objs.add(obj);
        }
        return objs;
    }

}
