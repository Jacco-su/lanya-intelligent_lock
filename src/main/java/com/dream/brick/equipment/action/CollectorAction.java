package com.dream.brick.equipment.action;


import com.alibaba.fastjson.JSON;
import com.dream.brick.admin.bean.Department;
import com.dream.brick.admin.dao.IDeptDao;
import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.dao.CollectorDao;
import com.dream.brick.equipment.dao.QgdisDao;
import com.dream.brick.listener.SessionData;
import com.dream.framework.dao.Pager;
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
import com.dream.util.StringUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 采集器 操作实现类
 */
@Controller
@Scope("prototype")
@RequestMapping("/collector")

public class CollectorAction {


    @Resource
    private CollectorDao collectorDao;
    @Resource
    private IDeptDao deptDao;
    @Resource
    private QgdisDao qgdisDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/prList")
    public String prList(String collectorId, HttpServletRequest request)
            throws Exception {
        return "admin/collector/lists";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, Pager pager,String deptId)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        if(StringUtils.isNotEmpty(deptId)){
            Department department =deptDao.find(Department.class,deptId);
            deptId=department.getAreacode();
        }
        List<Collector> list = collectorDao.findCollectorList(deptId,pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }


    @RequestMapping("/prAdd")
    public String prAdd(ModelMap model,String deptId, String dissName) {
        if(StringUtils.isNotEmpty(deptId)){
            Department department =deptDao.find(Department.class,deptId);
            deptId=department.getAreacode();
        }
        model.addAttribute("qgdisList", JSON.toJSONString(qgdisDao.findQgdisList(deptId,dissName)));
        return "admin/collector/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute Collector collector, HttpServletRequest request) {
        String message = "";
        if (collector.getDis() == null) {
            message = StringUtil.jsonValue("3", "请选择站点");
        } else {
            try {
                Map<String,String>  params=new HashMap<>();
                params.put("ccode",collector.getCcode());
                List<Collector> collectorList=collectorDao.findCollector(params);
                if(collectorList.size()>0){
                    return  StringUtil.jsonValue("0", "采集器ID存在重复，禁止添加！");
                }
                collector.setCdate(FormatDate.getYMdHHmmss());
                collectorDao.save(collector);
                SessionData.createSyslog(request,1, "添加采集器");
                message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
            }
        }
        return message;

    }

    @RequestMapping("/prUpdate")
    public String prUpdate(String id, ModelMap model, String deptId, String dissName) throws ParseException {
        Collector collector = collectorDao.find(Collector.class, id);
        model.addAttribute("collectora", collector);
        if(StringUtils.isNotEmpty(deptId)){
            Department department =deptDao.find(Department.class,deptId);
            deptId=department.getAreacode();
        }
        model.addAttribute("qgdisList", JSON.toJSONString(qgdisDao.findQgdisList(deptId,dissName)));


        return "admin/collector/update";
    }

    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Collector collector = collectorDao.find(Collector.class, id);
        model.addAttribute("collectora", collector);
        return "admin/collector/view";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute Collector collector, HttpServletRequest request) {
        String message = "";
        try {
            collectorDao.update(collector);
            SessionData.createSyslog(request,2, "更新采集器");
            message = StringUtil.jsonValue("1", AppMsg.UPDATE_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.UPDATE_ERROR);
        }
        return message;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id, HttpServletRequest request) {
        String message = "";
        String hql = "select count(1) from Collectore t where t.collector.id=?";
        int count = collectorDao.getResultNumber(hql, id);
        if (count > 0) {
            message = StringUtil.jsonValue("0", "该采集器有控制器，不允许删除！");
            //101该采集器有控制器，不允许删除
            return message;
        }
        try {
            Collector collector = collectorDao.find(Collector.class, id);
            collectorDao.delete(collector);
            SessionData.createSyslog(request,3, "删除采集器");
            message = StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.DEL_ERROR);
        }
        return message;
    }
}
