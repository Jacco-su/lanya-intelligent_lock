package com.dream.brick.equipment.action;


import com.alibaba.fastjson.JSON;
import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.bean.Collectore;
import com.dream.brick.equipment.dao.CollectorDao;
import com.dream.brick.equipment.dao.CollectoreDao;
import com.dream.brick.listener.BasicData;
import com.dream.framework.dao.Pager;
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
import com.dream.util.StringUtil;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 采集器 操作实现类
 */
@Controller
@Scope("prototype")
@RequestMapping("/collectore")

public class CollectoreAction {


    @Resource
    private CollectoreDao collectoreDao;
    @Resource
    private CollectorDao collectorDao;

    //    private QgdisDao qgdisDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/prList")
    public String prList()
            throws Exception {
        return "admin/collectore/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, Pager pager,String deptId)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Collectore> list = collectoreDao.findCollectoreList(deptId,pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }


    @RequestMapping("/prAdd")
    public String prAdd(ModelMap modelMap,String deptId, Pager pager) {
//        modelMap.addAttribute("collectorList", JSON.toJSONString(collectorDao.findAllCollector()));
//        model.addAttribute("collectoreId", collectoreId);
        modelMap.addAttribute("collectorList",JSON.toJSONString(collectorDao.findCollectorList(deptId,pager)));
        return "admin/collectore/add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute Collectore collectorea) {
        String message = "";
        try {
            collectorea.setCeDate(FormatDate.getYMdHHmmss());
//                collectorea.setCeCode(collectorea.getCeCode());
            collectoreDao.addCollectore(collectorea);
            message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        return message;
    }

//            try{
//                Area area= BasicData.findAreaByAreacode(disa.getAreacode());

//                data.setAreaname(area.getAreaname());
//                data.setAddress(disa.getAddress().trim());
//                collctoreDao.save(disa);
//                message= StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
//            }catch(Exception e){
//                message=StringUtil.jsonValue("0",AppMsg.ADD_ERROR);
//            }
//            try {
//                Area area = BasicData.findAreaByAreacode(disa.getAreacode());
//                return qgdisDao.findQgdisByAreacode(areacode);
//                disa.setAreaname(area.getAreaname());
//                disa.setAddress(disa.getAddress().trim());
    //disa.setLock(disa.getLock());
//                disDao.save(disa);
//                message= StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
//             }catch(Exception e){
//                message=StringUtil.jsonValue("0",AppMsg.ADD_ERROR);
//            }
//        data.setId("1");
//        collectore.setCip(collectore.getCip());
//        collectore.setCdate(sdf.format(new Date().getTime()));
//        //initRolea(collectore, disIdList);
//
//        try {
//            collectoreDao.save(collectore);
//            //collectoreDao.addCollectore(collectore);
//            message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
//        } catch (Exception e) {
//            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
//        }


//
//    public void initRolea(Collectore collectore, String[] disIdList) {
////        if(list==null){
////            list=new String[0];
////        }
//        if (disIdList == null) {
//            disIdList = new String[0];
//        }
////        List<UserRole> roleList=new ArrayList<UserRole>();
////        for(String roleId:list){
////            if("".equals(roleId)){
////                continue;
////            }
////            UserRole ur=new UserRole();
////            Role role=new Role();
////            role.setRoId(roleId);
////            ur.setRole(role);
////            ur.setUser(user);
////            roleList.add(ur);
////        }
//        // user.setRoleList(roleList);
//        StringBuilder sb = new StringBuilder("");
//        for (String disId : disIdList) {
//            sb.append(disId).append(",");
//        }
//        String disIds = sb.toString();
//        if (disIds.length() > 0) {
//            disIds = disIds.substring(0, disIds.length() - 1);
//        }
//        collectore.setCollectoreDiss(disIds);
//    }


    @RequestMapping("/prUpdate")
    public String prUpdate(String id, ModelMap model,String deptId,Pager pager) {
        Collectore collectore = collectoreDao.find(Collectore.class, id);
        model.addAttribute("collectore", collectore);
        String str = JSON.toJSONString(collectorDao.findCollectorList(deptId,pager));
        if(str.equals("")||str==null){
            model.addAttribute("collectorList", "该站点下没有采集器");
        }else {
            model.addAttribute("collectorList", JSON.toJSONString(collectorDao.findCollectorList(deptId,pager)));
        }

        return "admin/collectore/update";
    }

    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Collectore collectore = collectoreDao.find(Collectore.class, id);
        model.addAttribute("collectorea", collectore);
        return "admin/collectore/view";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute Collectore collectore) {
        String message = "";
            try{
//                Collector collector= BasicData.findAreaByAreacode(collectore.getAreacode());
//                collectore.setAreaname(collector.getAreaname());
//                collectore.setAddress(collectore.getAddress().trim());
//                collectore.setName(collectore.getName().trim());
                collectoreDao.update(collectore);
                message=StringUtil.jsonValue("1",AppMsg.UPDATE_SUCCESS);
            }catch(Exception e){
                message=StringUtil.jsonValue("0",AppMsg.UPDATE_ERROR);
            }

        return message;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String id) {
        String message = "";
//        String hql = "select count(*) from locks t where t.collectoreId=?";
//        int count = collectoreDao.getResultNumber(hql, id);
//        if (count > 0) {
//            message = StringUtil.jsonValue("0", AppMsg.getMessage("collectore101"));
//            //101该站点拥有智能锁，不允许删除
//            return message;
//        }
        try {
            Collectore collectore = collectoreDao.find(Collectore.class, id);
            collectoreDao.delete(collectore);
            message = StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.DEL_ERROR);
        }
        return message;
    }

//    @RequestMapping("/info")
//    @ResponseBody
//    public String getChildren(String parentId, HttpServletRequest request)
//            throws Exception {
//        JSONArray datas = new JSONArray();
//        String areacode= SessionData.getAreacode(request);
//        List<Department> children = deptDao.getChildren(parentId,areacode);
//        List<Department> deptAll=deptDao.findDeptIdAndName();
//        for (Department dept : children) {
//            Map<String, Object> tempMap = new HashMap<String, Object>();
//            tempMap.put("id", dept.getId());
//            tempMap.put("text", dept.getName());
//            tempMap.put("iconCls", "icon-ok");
//            for(Department d:deptAll){
//                if((dept.getId()).equals(d.getParentId())){
//                    tempMap.put("state", "closed");
//                    break;
//                }
//            }
//            datas.put(tempMap);
//        }
//        return datas.toString();
//    }

}
