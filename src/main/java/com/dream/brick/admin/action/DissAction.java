package com.dream.brick.admin.action;

import com.dream.brick.admin.bean.Department;
import com.dream.brick.admin.bean.Diss;
import com.dream.brick.admin.dao.DBBarkDao;
import com.dream.brick.admin.dao.IDeptDao;
import com.dream.brick.admin.dao.IDissDao;
import com.dream.framework.dao.Pager;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//import com.dream.brick.admin.bean.DissRole;

/**
 * 配电房 操作实现类 (改)
 */


@Controller()
@Scope("prototype")
@RequestMapping("/diss")
public class DissAction {

    @Resource
    private IDissDao dissDao;
    //	@Resource
//	private IRoleDao roleDao;
    @Resource
    private IDeptDao deptDao;
    @Resource
    private DBBarkDao dbBarkDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page, int rows, String deptId, String dissname, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<Diss> dissList = new ArrayList<Diss>();
        if (StringUtils.isNotBlank(deptId)) {
            dissList = dissDao.list(pager, deptId);
        } else if (StringUtils.isNotBlank(dissname)) {
            dissList = dissDao.query(pager, dissname);
        }
//		Map<String,List<Role>> urmap=dissDao.findAllDissRoles();
//		for(Diss diss : dissList){
// 			if(urmap.containsKey(diss.getId())){
//				diss.setRoles(urmap.get(diss.getId()));
//			}
//		}
        datas.put("total", pager.getTotalRow());
        datas.put("rows", dissList);
        return datas.toString();
    }


//	//	管理员
//	@RequestMapping("/index")
//	public String index(){
//		return "admin/list";
//	}

    //	人员管理
    @RequestMapping("/uindex")
    public String uindex() {
        return "admin/diss/list";
    }

    @RequestMapping("/prAdd")
    public String prAdd(String deptId, ModelMap model) {
        //List<Role> roles = roleDao.findRoleName(false);
        List<Department> deptList = deptDao.findDeptIdAndName();
        model.addAttribute("deptId", deptId);
        //model.addAttribute("roles", roles);
        model.addAttribute("deptList", deptList);
        return "admin/diss/add";
    }

//	@RequestMapping(value="/add",method = RequestMethod.POST)
//	@ResponseBody
//	public String add(@ModelAttribute Diss diss, String[] roIdList, String[] deptIdList)
//			throws Exception {
//		diss.setStatus(1);
//		diss.setPassword(MD5.encrytion(diss.getPassword()));
//		diss.setRdate(sdf.format(new Date().getTime()));
//		initRole(diss,roIdList,deptIdList);
//		String message="";
//		try{
//
//			dissDao.addDiss(diss);
//			message=StringUtil.jsonValue("1",AppMsg.ADD_SUCCESS);
//		}catch(Exception e){
//			message=StringUtil.jsonValue("0",AppMsg.ADD_ERROR);
//		}
//
//		return message;
//	}

//	@RequestMapping("/prUpdate")
//	public String prUpdate(String id, ModelMap model){
//		List<Role> roles = roleDao.findRoleName(false);
//		//List<Department> deptList=deptDao.findDeptIdAndName();
//		Diss diss = dissDao.findDissById(id);
//		StringBuffer roIds = new StringBuffer();
//		for (Role o : diss.getRoles()) {
//			roIds.append(o.getRoId() + ",");
//		}
//		if (roIds.length() > 0) {
//			roIds.deleteCharAt(roIds.length() - 1);
//		}
//		model.addAttribute("diss", diss);
//		model.addAttribute("roIds", roIds);
//		//model.addAttribute("deptIds",diss.getDissDepts());
//		model.addAttribute("roles",roles);
//		//model.addAttribute("deptList",deptList);
//		return "admin/update";
//	}

//	@RequestMapping(value="/update",method = RequestMethod.POST)
//	@ResponseBody
//	public String update(@ModelAttribute Diss diss, String[] roIdList, String[] deptIdList) {
//
//		String message="";
//		try{
//			initRole(diss,roIdList,deptIdList);
//			diss.setStatus(1);
//			Diss oldDiss =dissDao.find(Diss.class, diss.getId());
//			diss.setPassword(oldDiss.getPassword());//密码不能修改
//			dissDao.updateDiss(diss);
//			message=StringUtil.jsonValue("1",AppMsg.UPDATE_SUCCESS);
//			for(String roleId:roIdList){
//				if((AppData.JIXAOROLEID).equals(roleId)){
//					//如果是绩效人员
//					String[] values=new String[3];
//					values[0]= diss.getDept().getId();
//					values[1]= diss.getId();
//					values[2]= diss.getDissname();
//					dissDao.changeInputDissId(values);
//					//考核大项由该人员负责
//				}
//			}
//
//		}catch(Exception e){
//			e.printStackTrace();
//			message=StringUtil.jsonValue("0",AppMsg.UPDATE_ERROR);
//		}
//
//		return message;
//	}


//	public void initRole(Diss diss, String[] list, String[] deptIdList){
//		if(list==null){
//			list=new String[0];
//		}
//		if(deptIdList==null){
//			deptIdList=new String[0];
//		}
//		List<DissRole> roleList=new ArrayList<DissRole>();
//		for(String roleId:list){
//			if("".equals(roleId)){
//				continue;
//			}
//			DissRole ur=new DissRole();
//			Role role=new Role();
//			role.setRoId(roleId);
//			ur.setRole(role);
//			ur.setDiss(diss);
//			roleList.add(ur);
//		}
//		diss.setRoleList(roleList);
//		StringBuilder sb=new StringBuilder("");
//		for(String deptId:deptIdList){
//			sb.append(deptId).append(",");
//		}
//		String deptIds=sb.toString();
//		if(deptIds.length()>0){
//			deptIds=deptIds.substring(0,deptIds.length()-1);
//		}
//		diss.setDissDepts(deptIds);
//	}

//	@RequestMapping("/delete")
//	@ResponseBody
//	public String delete(String ids){
//		String msg = "success";
//		if (StringUtils.isNotBlank(ids)) {
//			for (String id : ids.split(",")) {
//				if (!id.equals("admin")) {
//					Diss diss =dissDao.find(Diss.class, id);
//					diss.setStatus(0);
//					dissDao.deleteDiss(diss);
//				} else {
//					msg = AppMsg.getMessage("diss100");
//					//admin用户不能被删除! AppMsg.UPDATE_ERROR
//				}
//			}
//		}
//		return msg;
//	}

//	@RequestMapping("/initPassword")
//	@ResponseBody
//	public String initPassword(String id){
//		Diss diss =new Diss();
//		diss.setId(id);
//		diss.setPassword(MD5.encrytion("111aaa"));
//		String message="";
//		try{
//			dissDao.updatePassword(diss);
//			message=StringUtil.jsonValue("1",AppMsg.getMessage("diss101")+"111aaa");
//			//密码重置成功，新密码为
//		}catch(Exception e){
//			message=StringUtil.jsonValue("0",AppMsg.getMessage("diss102"));
//			//102密码重置失败
//		}
//		return message;
//	}

    @RequestMapping("/prDbbark")
    public String prDbbark() {
        return "admin/diss/dbbark";
    }

//	@RequestMapping("/dbbark")
//	@ResponseBody
//	public String dbbark(){
//		String batpath=AppMsg.getMessage("batpath");
//		File file=new File(batpath);
//		if(!file.exists()){
//			return AppMsg.getMessage("batno");
//			//文件丢失，无法备份，请联系技术员
//		}
//		dbBarkDao.jixiaoBark();
//		String message="\u5907\u4EFD\u6210\u529F";
//		//备份成功
//		return message;
//	}

    /**
     * 用户更新自己的信息
     * ***/
//	@RequestMapping("/updateInfo")
//	@ResponseBody
//	public String updateInfo(Diss diss, HttpServletRequest request){
//		String message="";
//		Diss admin= SessionData.getLoginAdmin(request);
//		admin.setPhone(diss.getPhone());
//		admin.setEmail(diss.getEmail());
//		if (StringUtils.isNotEmpty(diss.getHaskh())) {
//			admin.setHaskh(diss.getHaskh());
//		}
//		try{
//			dissDao.update(admin);
//			request.getSession().setAttribute("admin", admin);
//			message=StringUtil.jsonValue("1",AppMsg.UPDATE_SUCCESS);
//		}catch(Exception e){
//			message=StringUtil.jsonValue("0",AppMsg.UPDATE_ERROR);
//		}
//		return message;
//	}
}
