package com.dream.brick.equipment.action;

import com.dream.brick.equipment.bean.Keyss;
import com.dream.brick.equipment.dao.IKeyssDao;
import com.dream.framework.dao.Pager;
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
import com.dream.util.StringUtil;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 钥匙 操作类
 */


@Controller
@Scope("prototype")
@RequestMapping("/keyss")
public class KeyssAction {

    @Resource
    private IKeyssDao ikeyssDao;


    @RequestMapping("/prList")
    public String prList(String keyssId, HttpServletRequest request) throws Exception {
        return "admin/keyss/list";
    }


    @RequestMapping("/list")
    @ResponseBody
    public String List(int page, int rows, Pager pager) throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Keyss> list = ikeyssDao.findKeyssList(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);

        return datas.toString();
    }


    @RequestMapping("/prAdd")
    public String prAdd() {
        return "admin/keyss/add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Keyss keyss) {
        String message = "";
        // Keyss keyss = BasicData.findKeyssByUserName(disa.getUserName());

        try {
            keyss.setKeyssDate(FormatDate.getYMdHHmmss());
            ikeyssDao.save(keyss);
            message = StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        return message;
    }


//    @RequestMapping
//    public String delete(String id) {
//        String mess
//
//
//
//
//
//        return null;
//    }


}
