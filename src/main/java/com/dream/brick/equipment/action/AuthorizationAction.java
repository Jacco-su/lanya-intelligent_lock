//package com.dream.brick.equipment.action;
//
//
//import com.dream.brick.equipment.bean.Authorization;
//
//import com.dream.brick.equipment.dao.IAuthorizationDao;
//
//import com.dream.brick.listener.BasicData;
//import com.dream.framework.dao.Pager;
//import com.dream.util.AppMsg;
//import com.dream.util.StringUtil;
//import org.json.JSONObject;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * 授权记录 操作 实现类
// *
// * @author ml
// *
// */
//@Controller
//@Scope("prototype")
//@RequestMapping("/authorization")
//public class AuthorizationAction {
//		@Resource
//		private IAuthorizationDao authorizationDao;
//
//		@RequestMapping("/prList")
//		public String prList(String authorizationId, HttpServletRequest request)
//				throws Exception {
//			return "admin/authorization/jllist";
//		}
//
//		@RequestMapping("/list")
//		@ResponseBody
//		public String list(int page, int rows, String deptId, String uName, Pager pager)
//				throws Exception {
//			pager.setCurrentPage(page);
//			pager.setPageSize(rows);
//			JSONObject datas = new JSONObject();
//			List<Authorization> list = authorizationDao.findAuthorizationList(pager);
//			datas.put("total", pager.getTotalRow());
//			datas.put("rows", list);
//			return datas.toString();
//		}
//
////		@RequestMapping("/prAdd")
////		public String prAdd(ModelMap model){
////			return "admin/authorization/add";
////		}
//
////		@RequestMapping(value = "/add", method = RequestMethod.POST)
////		@ResponseBody
////		public String add(@ModelAttribute Authorization authorizationa){
////			String message="";
////			try{
////				//Area area= BasicData.findAreaByAreacode(orga.getAreacode());
////				//authorizationa.setAdminname(admina.getAdminname());
////				//authorizationa.setAddress(authorizationa.getAddress().trim());
////				authorizationDao.save(authorizationa);
////				message=StringUtil.jsonValue("1",AppMsg.ADD_SUCCESS);
////			}catch(Exception e){
////				message=StringUtil.jsonValue("0",AppMsg.ADD_ERROR);
////			}
////			return message;
////		}
//
////		@RequestMapping("/prUpdate")
////		public String prUpdate(String id, ModelMap model){
////			Authorization authorizationa = authorizationDao.find(Authorization.class, id);
////			model.addAttribute("authorizationa", authorizationa);
////			return "admin/authorization/update";
////		}
//
//		@RequestMapping("/prView")
//		public String prView(String id, ModelMap model){
//			Authorization authorizationa = authorizationDao.find(Authorization.class, id);
//			model.addAttribute("authorizationa", authorizationa);
//			return "admin/authorization/view";
//		}
//
//
////		@RequestMapping(value = "/update", method = RequestMethod.POST)
////		@ResponseBody
////		public String update(@ModelAttribute Authorization authorizationa){
////			String message="";
////			try{
//////				Area area= BasicData.findAreaByAreacode(orga.getAreacode());
//////				authorizationa.setAreaname(area.getAreaname());
////				//authorizationa.setAddress(authorizationa.getAddress().trim());
////				//authorizationa.setName(authorizationa.getName().trim());
////				authorizationDao.update(authorizationa);
////				message=StringUtil.jsonValue("1",AppMsg.UPDATE_SUCCESS);
////			}catch(Exception e){
////				message=StringUtil.jsonValue("0",AppMsg.UPDATE_ERROR);
////			}
////			return message;
////		}
////		@RequestMapping("/delete")
////		@ResponseBody
////		public String delete(String id){
////			String message="";
////			String hql="select count(*) from Authorization t where t.authorizationId=?";
////			int count=authorizationDao.getResultNumber(hql,id);
////			if(count>0){
////				message=StringUtil.jsonValue("0",AppMsg.getMessage("authorizationa101"));
////                //101该区域拥有部门，不允许删除
////                return message;
////			}
////			try{
////				Authorization orga = authorizationDao.find(Authorization.class, id);
////				authorizationDao.delete(orga);
////				message=StringUtil.jsonValue("1",AppMsg.DEL_SUCCESS);
////			}catch(Exception e){
////				message=StringUtil.jsonValue("0",AppMsg.DEL_ERROR);
////			}
////			return message;
////		}
//
//}
