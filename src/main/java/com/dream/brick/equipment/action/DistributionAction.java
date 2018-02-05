package com.dream.brick.equipment.action;


import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.QgdisDao;
import com.dream.framework.dao.Pager;
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
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

import static com.dream.brick.listener.BasicData.qgdisDao;

//import com.dream.brick.equipment.bean.Qgorg;

/**
 * 站点 操作实现类
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
        return "admin/diss/list";
    }


    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, String deptId, String dissName, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Qgdis> list = disDao.findQgdisList(deptId, dissName, pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }

    @RequestMapping("/prAdd")
    public String prAdd(String deptId, ModelMap model) {
        model.addAttribute("deptId", deptId);
        return "admin/diss/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute Qgdis disa) {
        String message = "";
        try {
            disa.setCreateTime(FormatDate.getYMdHHmmss());
            disDao.addDis(disa);
            message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        return message;
    }

    @RequestMapping("/prUpdate")
    public String prUpdate(String id, ModelMap model) {
        Qgdis qgdis = qgdisDao.find(Qgdis.class, id);
        model.addAttribute("qgdis", qgdis);
        return "admin/diss/update";
    }


    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Qgdis disa = disDao.find(Qgdis.class, id);
        model.addAttribute("disa", disa);
        return "admin/diss/view";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute Qgdis disa) {
        String message = "";
        try {
            disa.setAddress(disa.getAddress().trim());
            disa.setName(disa.getName().trim());
            disa.setCreateTime(disa.getCreateTime());
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
//        String hql = "from Locks t where t.id=?";
        try {
//            int count = disDao.getResultNumber(hql, id);
//            if (count > 0) {
//                message = StringUtil.jsonValue("0", AppMsg.getMessage("disa101"));
//                //101该配电房拥有智能锁，不允许删除
//                return message;
//            }
            Qgdis disa = disDao.find(Qgdis.class, id);
            disDao.delete(disa);
            message = StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.DEL_ERROR);
        }
        return message;
    }

}
