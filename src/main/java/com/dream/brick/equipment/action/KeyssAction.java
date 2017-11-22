package com.dream.brick.equipment.action;

import com.dream.brick.equipment.bean.Keyss;
import com.dream.brick.equipment.dao.IKeyssDao;
import com.dream.framework.dao.Pager;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/keyss")
public class KeyssAction {


    private IKeyssDao ikeyssDao;

    @RequestMapping("/prList")
    public String prList(String keyssId, HttpServletRequest request) throws Exception {
        return "admin/keyss/list";
    }

    public String List(int page, int rows, Pager pager) {
        pager.setCurrentPage(page);
        pager.getPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Keyss> list = ikeyssDao.findKeyssList(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);

        return datas.toString();
    }
}
