package com.dream.brick.equipment.action;


import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.CollectorDao;
import com.dream.brick.equipment.dao.QgdisDao;
import com.dream.brick.listener.BasicData;
import com.dream.brick.listener.SessionData;
import com.dream.framework.dao.Pager;
import com.dream.util.AppMsg;
import com.dream.util.StringUtil;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 采集器 操作实现类
 */
@Controller
@Scope("prototype")
@RequestMapping("/collector")

public class CollectorAction {


    @Resource
    private CollectorDao collectorDao;

    private QgdisDao qgdisDao;

    @RequestMapping("/prList")
    public String prList(String collectorId, HttpServletRequest request)
            throws Exception {
        return "admin/collector/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, String deptId, String disId, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Collector> list = collectorDao.findCollectorList(pager, disId);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }


    @RequestMapping("/prAdd")
    public String prAdd(String qgdisId, ModelMap model, HttpServletRequest request) {
        //String areacode = SessionData.getAreacode(request);
        //List<Qgdis> qgdisList = BasicData.findQgdisByAreacode(areacode);
        List<Qgdis> qgdisList = qgdisDao.findDisIdAndName();
        model.addAttribute("qgdisList", qgdisList);
        model.addAttribute("qgdisId", qgdisId);
        return "admin/collector/add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute Collector collector, String[] disIdList) {
        String message = "";
//            try{
//                Area area= BasicData.findAreaByAreacode(disa.getAreacode());

//                disa.setAreaname(area.getAreaname());
//                disa.setAddress(disa.getAddress().trim());
//                disDao.save(disa);
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
        //collector.setStatus(1);
        //collector.setPassword(MD5.encrytion(user.getPassword()));
        //collector.setRdate(sdf.format(new Date().getTime()));
//        initRolea(collector, disIdList);
//
//        try {
//           // collectorDao.save(collector);
//            collectorDao.addCollector(collector);
//            message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
//        } catch (Exception e) {
//            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
//        }
//
        return message;
    }
//
//    public void initRolea(Collector collector, String[] disIdList) {
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
//        collector.setCollectorDiss(disIds);
//    }


    @RequestMapping("/prUpdate")
    public String prUpdate(String id, ModelMap model) {
        Collector collector = collectorDao.find(Collector.class, id);
        model.addAttribute("collector", collector);
        return "admin/collector/update";
    }

    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Collector collector = collectorDao.find(Collector.class, id);
        model.addAttribute("collector", collector);
        return "admin/collector/view";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute Collector collector) {
        String message = "";
//            try{
//                Area area= BasicData.findAreaByAreacode(disa.getAreacode());
//                disa.setAreaname(area.getAreaname());
//                disa.setAddress(disa.getAddress().trim());
//                disa.setName(disa.getName().trim());
//                disDao.update(disa);
//                message=StringUtil.jsonValue("1",AppMsg.UPDATE_SUCCESS);
//            }catch(Exception e){
//                message=StringUtil.jsonValue("0",AppMsg.UPDATE_ERROR);
//            }

        return message;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        String message = "";
        String hql = "select count(*) from locks t where t.collectorId=?";
        int count = collectorDao.getResultNumber(hql, id);
        if (count > 0) {
            message = StringUtil.jsonValue("0", AppMsg.getMessage("collector101"));
            //101该配电房拥有智能锁，不允许删除
            return message;
        }
        try {
            Collector collector = collectorDao.find(Collector.class, id);
            collectorDao.delete(collector);
            message = StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        } catch (Exception e) {
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
