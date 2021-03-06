package com.dream.brick.equipment.action;

import com.dream.brick.equipment.bean.OpenLog;
import com.dream.brick.equipment.dao.IOpenLogDao;
import com.dream.framework.dao.Pager;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: OpenLockAction.java
 * @Description: 开关锁日志
 * @date 2018-03-12 上午8:54
 */
@Controller
@Scope("prototype")
@RequestMapping("/openlog")
public class OpenLockAction {
	@Resource
	private IOpenLogDao openLogDao;
	@RequestMapping("/prList")
	public String prList()
			throws Exception {
		return "admin/openlog/list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public String list(int page, int rows,
					   String deptId,
					   String userId,
					   String lockName,
					   Pager pager)
			throws Exception {
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		JSONObject datas = new JSONObject();
		List<OpenLog> list = openLogDao.findLockLogList(deptId, userId, lockName, pager);
		datas.put("total", pager.getTotalRow());
		datas.put("rows", list);
		return datas.toString();
	}

	@RequestMapping("/locklist")
	@ResponseBody
	public String locklist(int page, int rows,
						   String deptId,
						   String lockName,
						   Pager pager)
			throws Exception {
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		JSONObject datas = new JSONObject();
		List<OpenLog> list = openLogDao.qLockLogList(deptId, lockName, pager);
		datas.put("total", pager.getTotalRow());
		datas.put("rows", list);
		return datas.toString();
	}

	@RequestMapping("/query")
	@ResponseBody
	public String queryList(int page, int rows,
							String deptId,

							String authName,
							String authStartTime,
							String authEndTime,
							String userId,
							Pager pager)
			throws Exception {
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		JSONObject datas = new JSONObject();
		List<OpenLog> list = openLogDao.queryList(deptId, authName, authStartTime, authEndTime, userId, pager);
		datas.put("total", pager.getTotalRow());
		datas.put("rows", list);
		return datas.toString();
	}

	@RequestMapping("/queryTime")
	@ResponseBody
	public String areaList(int page,
						   int rows,
						   String deptId,
						   String userId,
						   String openTime,
						   String createTime,
						   HttpServletRequest request,
						   Pager pager) throws Exception {
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		JSONObject datas = new JSONObject();
//				Map<String, String> params=new HashMap<>();
//				params.put("openTime",openTime);
//				params.put("createTime",createTime);
		List<OpenLog> openLogList = openLogDao.queryTime(deptId, openTime, createTime, pager);
		List<OpenLog> timeList = openLogDao.findList(openTime, createTime, userId, pager);
		datas.put("total", pager.getTotalRow());
		datas.put("rows", openLogList);
		return datas.toString();

	}

	@RequestMapping("/qTime")
	@ResponseBody
	public String areaList(int page,
						   int rows,
						   String userId,
						   String openTime,
						   String createTime,
						   HttpServletRequest request,
						   Pager pager) throws Exception {
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		JSONObject datas = new JSONObject();

		List<OpenLog> timeList = openLogDao.findList(openTime, createTime, userId, pager);
		datas.put("total", pager.getTotalRow());
		datas.put("rows", timeList);
		return datas.toString();

	}



}
