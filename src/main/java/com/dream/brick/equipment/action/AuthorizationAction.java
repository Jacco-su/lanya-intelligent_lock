package com.dream.brick.equipment.action;


import com.dream.brick.equipment.bean.Authorization;

import com.dream.brick.equipment.dao.IAuthorizationDao;

import com.dream.brick.listener.BasicData;
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
 * 授权记录 操作 实现类
 *
 * @author ml
 */
@Controller
@Scope("prototype")
@RequestMapping("/authorization")
public class AuthorizationAction {
    @Resource
    private IAuthorizationDao authorizationDao;

    @RequestMapping("/prList")
    public String prList(String authorizationId, HttpServletRequest request)
            throws Exception {
        return "admin/authorization/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, String name, String username, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Authorization> list = authorizationDao.findAuthorizationList(pager);
        List<Authorization> list2 = authorizationDao.findAllAuthorization();
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        datas.put("rows2", list2);
        return datas.toString();
    }



    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        Authorization authorizationa = authorizationDao.find(Authorization.class, id);
        model.addAttribute("authorizationa", authorizationa);
        return "admin/authorization/view";
    }

    @RequestMapping("/workView")
    public String workView(String id, ModelMap model) {
        Authorization authorizationa = authorizationDao.find(Authorization.class, id);
        model.addAttribute("authorizationa", authorizationa);
        return "admin/authorization/workview";
    }


//		@RequestMapping(value = "/update", method = RequestMethod.POST)
//		@ResponseBody
//		public String update(@ModelAttribute Authorization authorizationa){
//			String message="";
//			try{
////				Area area= BasicData.findAreaByAreacode(orga.getAreacode());
////				authorizationa.setAreaname(area.getAreaname());
//				//authorizationa.setAddress(authorizationa.getAddress().trim());
//				//authorizationa.setName(authorizationa.getName().trim());
//				authorizationDao.update(authorizationa);
//				message=StringUtil.jsonValue("1",AppMsg.UPDATE_SUCCESS);
//			}catch(Exception e){
//				message=StringUtil.jsonValue("0",AppMsg.UPDATE_ERROR);
//			}
//			return message;
//		}


    @RequestMapping("/prAdd")
    public String prAdd(String deptId, ModelMap model) {
        //List<Role> roles = roleDao.findRoleName(false);
//        List<Authorization> deptList = authorizationDao.findDeptIdAndName();
//        model.addAttribute("deptId", deptId);
        //model.addAttribute("roles", roles);
//        model.addAttribute("deptList", deptList);
        return "admin/authorization/add";
    }

    // 离线授权


    @RequestMapping("/prOffLine")
    public String proffLine(String authorizationId, HttpServletRequest request)
            throws Exception {
        return "commons/notBuild";
    }

    //在线授权
    @RequestMapping("/prOnLine")

    public String pronLine(String authorizationId, HttpServletRequest request)
            throws Exception {
        return "admin/authorization/onLine";
    }

    @RequestMapping("/onLine")
    @ResponseBody
    public String offLine(int page, int rows, String authorizationId, String uName, Pager pager) throws Exception {
        return null;
    }
}