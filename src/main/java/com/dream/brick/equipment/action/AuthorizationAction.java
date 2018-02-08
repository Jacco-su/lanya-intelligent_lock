package com.dream.brick.equipment.action;


import com.alibaba.fastjson.JSON;
import com.dream.brick.equipment.bean.Authorization;
import com.dream.brick.equipment.dao.IAuthorizationDao;
import com.dream.brick.equipment.dao.IKeyssDao;
import com.dream.brick.equipment.dao.QgdisDao;
import com.dream.framework.dao.Pager;
import com.dream.util.AppMsg;
import com.dream.util.StringUtil;
import com.dream.util.StringUtils;
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
    @Resource
    private IKeyssDao ikeyssDao;
    @Resource
    private QgdisDao disDao;

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
    @RequestMapping("/prViewAuth")
    public String prViewAuth( ModelMap model) {

        return "admin/authorization/authList";
    }
    @RequestMapping("/workView")
    public String workView(String id, ModelMap model) {
        Authorization authorizationa = authorizationDao.find(Authorization.class, id);
        model.addAttribute("authorizationa", authorizationa);
        return "admin/authorization/workview";
    }


    @RequestMapping("/prUpdate")
    public String prUpdate(String Id, ModelMap model) {
//        Authorization authorizationa = authorizationDao.find(Authorization.class, Id);
//        model.addAttribute("authorization", authorizationa);
        return "admin/authorization/update";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute Authorization authorizationa) {
        String message = "";
        try {
//				Area area= BasicData.findAreaByAreacode(orga.getAreacode());
//				authorizationa.setAreaname(area.getAreaname());
            //authorizationa.setAddress(authorizationa.getAddress().trim());
            //authorizationa.setName(authorizationa.getName().trim());
            authorizationDao.update(authorizationa);
            message = StringUtil.jsonValue("1", AppMsg.UPDATE_SUCCESS);
        } catch (Exception e) {
            message = StringUtil.jsonValue("0", AppMsg.UPDATE_ERROR);
        }
        return message;
    }


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
    @RequestMapping("/distribution")
    @ResponseBody
    public String getDistributionAction(String disaId){
        if (StringUtils.isNotEmpty(disaId)) {
            return JSON.toJSONString(authorizationDao.findListDisa(disaId));
        } else {

            return JSON.toJSONString(disDao.findAllQgdis());
        }

    }
//    @RequestMapping("/disloke")
//    @ResponseBody
//    public String getDisLoke(){
//            return JSON.toJSONString(disDao.findAllQgdis());
//    }



    @RequestMapping("/user")
    @ResponseBody
    public String getUserAction(String disaId){
        return JSON.toJSONString(authorizationDao.findList("from User where deptId="+disaId));
    }
    @RequestMapping("/disa/collector")
    @ResponseBody
    public String getCollectorAction(String disaId){
        return JSON.toJSONString(authorizationDao.findList("from Collector where disId="+disaId));
    }
    @RequestMapping("collector/collectore")
    @ResponseBody
    public String getCollectoreAction(String collectorId){
        return JSON.toJSONString(authorizationDao.findList("from Collectore where cid="+collectorId));
    }

    @RequestMapping("/keys")
    @ResponseBody
    public String getKeysAction(){
        return JSON.toJSONString(ikeyssDao.findAllKeyss());
    }

    @RequestMapping("/disa/locks")
    @ResponseBody
    public String getLocksAction(String disaId) {
        return JSON.toJSONString(authorizationDao.findList("from Locks where dissId=" + disaId));
    }
}
