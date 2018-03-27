package com.dream.brick.listener;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.dream.brick.admin.bean.Department;
import com.dream.brick.admin.bean.Syslog;
import com.dream.brick.admin.bean.User;
import com.dream.brick.equipment.bean.Qgorg;
import com.dream.util.FormatDate;

import javax.servlet.http.HttpServletRequest;

public class SessionData {
	/**
	 * 获取session中的当前登录用户
	 * @return
	 */
	public static User getLoginAdmin(HttpServletRequest request){
		if (request.getSession().getAttribute("admin")!=null) {
			return (User)request.getSession().getAttribute("admin");
		}
		return null;
	}
	
	public static User getLoginZhAdmin(HttpServletRequest request){
		if (request.getSession().getAttribute("zhadmin")!=null) {
			return (User)request.getSession().getAttribute("zhadmin");
		}
		return null;
	}
	
	public static String getOperateYear(HttpServletRequest request){
		String operateYear=(String)request.getSession().getAttribute("operateYear");
		if (operateYear!=null){
			return operateYear;
		}else{
			return "";
		}
	}
	
	public static String getAdminId(HttpServletRequest request){
		if (request.getSession().getAttribute("admin")!=null) {
			User user=(User)request.getSession().getAttribute("admin");
			return user.getId();
		}
		return null;
	}
	public static String getAdminDeptId(HttpServletRequest request){
		if (request.getSession().getAttribute("admin")!=null) {
			User user=(User)request.getSession().getAttribute("admin");
			return user.getDept().getId();
		}
		return null;
	}
	public static Qgorg getLoginQgorg(HttpServletRequest request){
		if (request.getSession().getAttribute("seqgorg")!=null) {
			return (Qgorg)request.getSession().getAttribute("seqgorg");
		}
		return null;
	}	
	public static String getAreacode(HttpServletRequest request){
		if(request.getSession().getAttribute("seareacode")!=null) {
			return (String)request.getSession().getAttribute("seareacode");
		}
		return null;
	}
	public static Department getDept(HttpServletRequest request){
		if (request.getSession().getAttribute("dept")!=null) {
			return (Department)request.getSession().getAttribute("dept");
		}
		return null;
	}
	public static void createSyslog(HttpServletRequest request, int type, String content){
		if(request.getSession().getAttribute("admin")!=null){
			User user=(User)request.getSession().getAttribute("admin");
			String ip=request.getRemoteHost();
			Syslog syslog=new Syslog();
			syslog.setType(type);
			syslog.setIp(ip);
			syslog.setUsername(user.getUsername());
			syslog.setContent(content);
			syslog.setCreateTime(FormatDate.getYMdHHmmss());
			BasicData.saveSyslog(syslog);
		}
	}
	public void test() {
		try {
			//oneMethod();
			System.out.println("condition 1");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("condition 2");
		} catch(Exception e) {
			System.out.println("condition 3");
		} finally {
			System.out.println("finally");
		}
	}
	public static void main(String[] args) {
		char c=66;
		SessionData s=new SessionData();
		s.test();
	}
}
