package com.dream.brick.equipment.action;

import com.dream.brick.admin.bean.Department;
import com.dream.brick.admin.dao.IDeptDao;
import com.dream.brick.equipment.bean.*;
import com.dream.brick.equipment.dao.*;
import com.dream.brick.listener.SessionData;
import com.dream.framework.dao.Pager;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钥匙 操作类
 */


@Controller
@Scope("prototype")
@RequestMapping("/chart")
public class ChartsAction {

    private IChartsDao chartsDao;

    @Resource
    private QgdisDao disDao;

    @Resource
    private IDeptDao deptDao;

    @Resource
    private ILocksDao ilocksDao;

    @Resource
    private IOpenLogDao openLogDao;

    @Resource
    private IKeyssDao ikeyssDao;

    @Resource
    private IAuthLogDao authLogDao;

    @RequestMapping("/prList")
    public String prList(String orgId, HttpServletRequest request)
            throws Exception {
        return "admin/charts/list";
    }


    @RequestMapping("/List")
    @ResponseBody
    public String list(int page, int rows, String deptId, String username, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Charts> list = chartsDao.findChartsList(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }
    /**
     * @author       陶乐乐(wangyiqianyi@qq.com)
     * @Description: 站点统计
     * @date         2018-04-17 18:15:39
     * @params
     * @return
     * @throws
     */
    @RequestMapping("/kbsReport")
    @ResponseBody
    public String kbsList(int page,
                          int rows,
                          String deptId,
                          String dissName,
                          String authStartTime,
                          String authEndTime,
                          Pager pager){
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Charts> list = new ArrayList<>();//chartsDao.findChartsList(pager);
        if(StringUtils.isNotEmpty(deptId)){
            Department department =deptDao.find(Department.class,deptId);
            deptId=department.getAreacode();
        }
        //
        List<Qgdis> qgdisList=disDao.findQgdisList(deptId,"",pager);
        if(qgdisList.size()>0){
            for (int i = 0; i <qgdisList.size() ; i++) {
                Charts charts=new Charts();
               List<Locks> locksList=ilocksDao.findLocksByDis(qgdisList.get(i).getId());
                Map<String, String> params=new HashMap<>();
                params.put("authStartTime",authStartTime);
                params.put("authEndTime",authEndTime);
               List<OpenLog> openLogList = openLogDao.findOpenLog(params);
               int count=0;
                for (int j = 0; j <openLogList.size() ; j++) {
                    OpenLog openLog=openLogList.get(j);
                    for (int k = 0; k <locksList.size() ; k++) {
                        Locks locks=locksList.get(k);
                        if(openLog.getLockNum().equals(locks.getLockCode().replace("-",""))){
                            count++;
                        }
                    }
                }
                charts.setDisId(qgdisList.get(i).getName());
                charts.setCount(count);
                list.add(charts);
            }
        }
        //pager.setTotalRow(list.size());
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }
    /**
     * @author       陶乐乐(wangyiqianyi@qq.com)
     * @Description: 站点统计
     * @date         2018-04-17 18:15:39
     * @params
     * @return
     * @throws
     */
    @RequestMapping("/kbsList")
    public String kbsList()
            throws Exception {
        return "admin/charts/kbsList";
    }
    /**
     * @author       陶乐乐(wangyiqianyi@qq.com)
     * @Description: 站点统计
     * @date         2018-04-17 18:15:39
     * @params
     * @return
     * @throws
     */
    @RequestMapping("/keysList")
    public String keysList()
            throws Exception {
        return "admin/charts/keysList";
    }
    /**
     * @author       陶乐乐(wangyiqianyi@qq.com)
     * @Description: 站点统计
     * @date         2018-04-17 18:15:39
     * @params
     * @return
     * @throws
     */
    @RequestMapping("/keysReport")
    @ResponseBody
    public String keysList(int page,
                          int rows,
                          String deptId,
                          String dissName,
                          String authStartTime,
                          String authEndTime,
                          Pager pager){
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Charts> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(deptId)){
            Department department =deptDao.find(Department.class,deptId);
            deptId=department.getAreacode();
        }
        List<Keyss> keyssList = ikeyssDao.findKeyssList(deptId,pager);
       // List<Keyss> keyssList = ikeyssDao.findKeyssUserList(deptId);
        if(keyssList.size()>0){
            for (int i = 0; i < keyssList.size(); i++) {
                Keyss keyss=keyssList.get(i);
                Charts charts=new Charts();
                charts.setKeyCode(keyss.getKeyssMAC());
                charts.setKeyName(keyss.getKeyssCode());
                 List<AuthLog> authLogList=authLogDao.findList(keyss.getKeyssMAC(),authStartTime,authEndTime);
                charts.setCount(authLogList.size());
                list.add(charts);
            }

        }
        //pager.setTotalRow(list.size());
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }
    //String areacode= SessionData.getAreacode(request);
    //List<Department> children = deptDao.getChildren(parentId,areacode);
    /**
     * @author       陶乐乐(wangyiqianyi@qq.com)
     * @Description: 站点统计
     * @date         2018-04-17 18:15:39
     * @params
     * @return
     * @throws
     */
    @RequestMapping("/areaList")
    public String areaList()
            throws Exception {
        return "admin/charts/areaList";
    }
    /**
     * @author       陶乐乐(wangyiqianyi@qq.com)
     * @Description: 站点统计
     * @date         2018-04-17 18:15:39
     * @params
     * @return
     * @throws
     */
    @RequestMapping("/areaReport")
    @ResponseBody
    public String areaList(int page,
                           int rows,
                           String deptId,
                           String dissName,
                           String authStartTime,
                           String authEndTime,
                           HttpServletRequest request,
                           Pager pager){
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Charts> list = new ArrayList<>();
        String areacode= SessionData.getAreacode(request);
        List<Department> deptList = deptDao.findDeptList(areacode,pager);
        if(deptList.size()>0){
            for (int i = 0; i < deptList.size(); i++) {
                Department department=deptList.get(i);
                Charts charts=new Charts();
                int count=0;
                List<Locks> locksList=ilocksDao.findLocksAreaList(department.getAreacode());
                Map<String, String> params=new HashMap<>();
                params.put("authStartTime",authStartTime);
                params.put("authEndTime",authEndTime);
                List<OpenLog> openLogList = openLogDao.findOpenLog(params);
                for (int j = 0; j <openLogList.size() ; j++) {
                    OpenLog openLog=openLogList.get(j);
                    for (int k = 0; k <locksList.size() ; k++) {
                        Locks locks=locksList.get(k);
                        if(openLog.getLockNum().equals(locks.getLockCode().replace("-",""))){
                            count++;
                        }
                    }
                }
                charts.setCount(count);
                charts.setKeyName(department.getName());
                list.add(charts);
            }
        }
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();

    }
}