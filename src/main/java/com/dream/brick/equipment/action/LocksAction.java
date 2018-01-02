package com.dream.brick.equipment.action;

import com.alibaba.fastjson.JSON;
import com.dream.brick.equipment.bean.Locks;
import com.dream.brick.equipment.dao.ILocksDao;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 门锁操作 类
 */
@Controller
@Scope("prototype")
@RequestMapping("/locks")
public class LocksAction {
    @Resource
    private QgdisDao disDao;
    @Resource
    private ILocksDao ilocksDao;



    @RequestMapping("/prList")
    public String prList(String locksId, HttpServletRequest request) throws Exception {
        return "admin/locks/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, Pager pager) throws Exception {

        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();

        List<Locks> list = ilocksDao.findLocksList(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);

        return datas.toString();
    }

    @RequestMapping("/prAdd")
    public String prAdd(ModelMap modelMap) {
        modelMap.addAttribute("dissList",JSON.toJSONString(disDao.findAllQgdis()));
        return "admin/locks/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String daa(@ModelAttribute Locks locks) {
        String message = "";
        locks.setLockNum(locks.getLockNum());
        locks.setLockCode(locks.getLockCode());
        locks.setLockDate(FormatDate.getYMdHHmmss());
        try {
            ilocksDao.save(locks);
            message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        return message;
    }





    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Locks lockss = ilocksDao.find(Locks.class, id);
        model.addAttribute("locks", lockss);
        return "admin/locks/view";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        String message = "";
        String hql = "select count(*) from Lock t where t.locksId=?";
        int count = ilocksDao.getResultNumber(hql, id);
        if (count > 0) {
            message = StringUtil.jsonValue("0", AppMsg.getMessage("locks101"));
            //101该配电房拥有智能锁，不允许删除
            return message;
        }
        try {
            Locks collector = ilocksDao.find(Locks.class, id);
            ilocksDao.delete(collector);
            message = StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.DEL_ERROR);
        }
        return message;
    }


}
