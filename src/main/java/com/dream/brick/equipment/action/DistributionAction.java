package com.dream.brick.equipment.action;


import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.QgdisDao;
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

//import com.dream.brick.equipment.bean.Qgorg;

/**
 * 配电房 操作实现类
 */
@Controller
@Scope("prototype")
@RequestMapping("/disa")

public class DistributionAction {


    @Resource
    private QgdisDao disDao;

    @RequestMapping("/prList")
    public String prList(String disId, HttpServletRequest request)
            throws Exception {
        return "admin/dis/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, boolean lock, String deptId, String username, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Qgdis> list = disDao.findQgdisList(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }

    @RequestMapping("/prAdd")
    public String prAdd(ModelMap model) {
        return "admin/dis/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute Qgdis disa) {
        String message = "";
//            try{
//                Area area= BasicData.findAreaByAreacode(disa.getAreacode());
//
//                disa.setAreaname(area.getAreaname());
//                disa.setAddress(disa.getAddress().trim());
//                disDao.save(disa);
//                message= StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
//            }catch(Exception e){
//                message=StringUtil.jsonValue("0",AppMsg.ADD_ERROR);
//            }
//
//            try {
//                Area area = BasicData.findAreaByAreacode(disa.getAreacode());
//
//                disa.setAreaname(area.getAreaname());
//                disa.setAddress(disa.getAddress().trim());
//                disa.setLock(disa.getLock());
//                disDao.save(disa);
//                message= StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
//             }catch(Exception e){
//                message=StringUtil.jsonValue("0",AppMsg.ADD_ERROR);
//            }

        return message;
    }

    @RequestMapping("/prUpdate")
    public String prUpdate(String id, ModelMap model) {
        Qgdis disa = disDao.find(Qgdis.class, id);
        model.addAttribute("disa", disa);
        return "admin/dis/update";
    }

    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Qgdis disa = disDao.find(Qgdis.class, id);
        model.addAttribute("disa", disa);
        return "admin/dis/view";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute Qgdis disa) {
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
        try {
            // Qgdis disa = BasicData.findAreaByAreacode(disa.getAreacode());
            disa.setAreaname(disa.getAreaname());
            disa.setAddress(disa.getAddress().trim());
            disa.setName(disa.getName().trim());
            disDao.update(disa);
            message = StringUtil.jsonValue("1", AppMsg.UPDATE_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.UPDATE_ERROR);
        }
        return message;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        String message = "";
        String hql = "select count(*) from t_lokes t where t.qgdisId=?";
        int count = disDao.getResultNumber(hql, id);
        if (count > 0) {
            message = StringUtil.jsonValue("0", AppMsg.getMessage("disa101"));
            //101该配电房拥有智能锁，不允许删除
            return message;
        }
        try {
            Qgdis disa = disDao.find(Qgdis.class, id);
            disDao.delete(disa);
            message = StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.DEL_ERROR);
        }
        return message;
    }
}
