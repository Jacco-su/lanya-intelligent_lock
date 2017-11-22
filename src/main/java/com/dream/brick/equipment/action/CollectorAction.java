package com.dream.brick.equipment.action;


import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.dao.CollectorDao;
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

    @RequestMapping("/prList")
    public String prList(String collectorId, HttpServletRequest request)
            throws Exception {
        return "admin/collector/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, String deptId, String username, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Collector> list = collectorDao.findCollectorList(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }

    @RequestMapping("/prAdd")
    public String prAdd(ModelMap model) {
        return "admin/collector/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute Collector collector) {
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

        return message;
    }

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
}
