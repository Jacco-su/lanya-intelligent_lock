package com.dream.brick.equipment.action;

//import com.dream.brick.equipment.bean.Dept;
//import com.dream.brick.equipment.dao.DeptInfoDao;
//@Controller
//@Scope("prototype")
//@RequestMapping("/deptinfo")
//public class DeptInfoAction {
//
//	@Resource
//	public DeptInfoDao deptDao;

//	@RequestMapping("/findDeptByCode")
//	@ResponseBody
//	public String findDeptByCode(HttpServletRequest request) throws Exception {
//		String deptcode= SessionData.getDeptcode(request);
//		List<Dept> deptList=deptDao.findDeptByCode(deptcode);
//
//		JSONArray jsonArray = new JSONArray();
//		if(deptList.size()>1){
//			for(Dept dept:deptList){
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("id", dept.getDeptcode());
//				jsonObject.put("text",dept.getDeptname());
//				jsonObject.put("hasChildren","true");
//				jsonObject.put("classes", "folder");
//				jsonObject.put("state", "closed");
//				JSONObject jsonOther = new JSONObject();
//				jsonOther.put("grade", dept.getGrade());
//				jsonOther.put("parentcode", dept.getParentcode());
//				jsonObject.put("attributes",jsonOther);
//				jsonArray.put(jsonObject);
//			}
//		}else{
//			Dept dept=deptList.get(0);
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("id", dept.getDeptcode());
//			jsonObject.put("text",dept.getDeptname());
//			jsonObject.put("hasChildren","true");
//			jsonObject.put("classes", "folder");
//			jsonObject.put("state", "closed");
//			JSONObject jsonOther = new JSONObject();
//			jsonOther.put("grade", dept.getGrade());
//			jsonOther.put("parentcode", dept.getParentcode());
//			jsonOther.put("islast", dept.getIslast());
//			jsonObject.put("attributes",jsonOther);
//			jsonArray.put(jsonObject);
//		}
//		return jsonArray.toString();
//	}
//

//	@RequestMapping("/findDeptByParentId")
//	@ResponseBody
//	public String findDeptByParentId(String parentId, HttpServletRequest request) throws Exception {
//		    List<Dept> deptList=deptDao.findDeptByParentId(parentId);
//		    JSONArray jsonArray = new JSONArray();
//			for(Dept dept:deptList){
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("id", dept.getDeptcode());
//				jsonObject.put("text",dept.getDeptname());
//				JSONObject jsonOther = new JSONObject();
//				jsonOther.put("grade", dept.getGrade());
//				jsonOther.put("islast", dept.getIslast());
//				jsonOther.put("deptname", dept.getDeptname());
//				jsonOther.put("parentcode", dept.getParentcode());
//				if(dept.getGrade()<=3){
//					jsonObject.put("hasChildren","true");
//					jsonObject.put("state", "closed");
//					jsonObject.put("classes", "folder");
//				}else{
//					jsonObject.put("hasChildren","false");
//				}
//				jsonObject.put("attributes",jsonOther);
//				jsonArray.put(jsonObject);
//			}
//			return jsonArray.toString();
//	}
//}
