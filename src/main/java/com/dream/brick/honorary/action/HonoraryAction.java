package com.dream.brick.honorary.action;

import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Qgorg;
import com.dream.brick.honorary.bean.Honorary;
import com.dream.brick.honorary.dao.impl.HonoraryDao;
import com.dream.brick.listener.BasicData;
import com.dream.brick.listener.SessionData;
import com.dream.util.AppMsg;
import com.dream.util.StringUtil;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作荣誉controller
 * Created by taller on 16/2/23.
 */
@Controller
@Scope("prototype")
@RequestMapping("/honorary")
public class HonoraryAction {
    @Resource
    private HonoraryDao honoraryDao;
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(int page, int rows, Pager pager) throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Honorary> honoraryList=honoraryDao.listAll(pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", honoraryList);
        System.out.println(datas.toString());
        return datas.toString();
    }
    @RequestMapping("/index")
    public String index(){
        return "honorary/list";
    }
    @RequestMapping(value = "/prAdd")
    public String prAdd(ModelMap model){
        List<Qgorg> qgorgList= BasicData.findAllQgorg();
        model.addAttribute("qgorgList", qgorgList);
        return "honorary/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(Honorary honorary, HttpServletRequest request){
        String message="";
         try {
        	 
        	 
        	 Qgorg qgorg=SessionData.getLoginQgorg(request);
        	 honorary.setAreacode(qgorg.getAreacode());
        	 honorary.setAreaname(qgorg.getAreaname());
        	 
        	 Qgorg qgorgs=(Qgorg)honoraryDao.find(Qgorg.class,honorary.getOrgid());
        	 honorary.setOrgname(qgorgs.getName());
             honoraryDao.save(honorary);
             message=StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
         }catch (Exception e){
             e.printStackTrace();
             message=StringUtil.jsonValue("0", AppMsg.ADD_SUCCESS);
         }
        return message;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String id){
    	String message="";
        try{
            Honorary honorary=honoraryDao.findHonoraryById(id);
            honoraryDao.delete(honorary);
            message=StringUtil.jsonValue("1", AppMsg.DEL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            message=StringUtil.jsonValue("0", AppMsg.DEL_ERROR);
        }
        return message;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Honorary honorary){
    	String message="";
        try{
            Qgorg qgorg= (Qgorg) honoraryDao.find(Qgorg.class, honorary.getOrgid());
            honorary.setOrgname(qgorg.getName());
            
            Honorary old=honoraryDao.findHonoraryById(honorary.getId());
            old.setYear(honorary.getYear());
            old.setHonoraryWork(honorary.getHonoraryWork());
            old.setCheckTime(honorary.getCheckTime());
            honoraryDao.update(old);
            message=StringUtil.jsonValue("1", AppMsg.UPDATE_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            message=StringUtil.jsonValue("0", AppMsg.UPDATE_ERROR);
        }
        return message;
    }
    @RequestMapping("/prUpdate")
    public String prUpdate(String id, ModelMap model){
        //List<Qgorg> qgorgList=BasicData.findAllQgorg();
        //model.addAttribute("qgorgList", qgorgList);
        Honorary honorary=honoraryDao.findHonoraryById(id);
        model.addAttribute("honorary", honorary);
        return "honorary/update";
    }
    @RequestMapping("/view")
    public String view(String id, ModelMap model){
        List<Qgorg> qgorgList=BasicData.findAllQgorg();
        model.addAttribute("qgorgList", qgorgList);
        Honorary honorary=honoraryDao.findHonoraryById(id);
        model.addAttribute("honorary", honorary);
        return "honorary/view";
    }
}
