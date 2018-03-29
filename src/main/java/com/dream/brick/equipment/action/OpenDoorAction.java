package com.dream.brick.equipment.action;

import com.dream.brick.equipment.bean.OpenDoor;
import com.dream.brick.equipment.bean.OpenLog;
import com.dream.brick.equipment.dao.IOpenDoorDao;
import com.dream.brick.equipment.dao.IOpenLogDao;
import com.dream.framework.dao.Pager;
import com.dream.util.extend.FtpUtil;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: OpenLockAction.java
 * @Description: 开锁日志
 * @date 2018-03-12 上午8:54
 */
@Controller
@Scope("prototype")
@RequestMapping("/opendoor")
public class OpenDoorAction {
    @Resource
	private IOpenDoorDao openDoorDao;
	@RequestMapping("/prList")
	public String prList()
			throws Exception {
		return "admin/opendoor/list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public String list(int page, int rows,String deptId,Pager pager)
			throws Exception {
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		JSONObject datas = new JSONObject();
		List<OpenDoor> list = openDoorDao.findDoorLogList(deptId,pager);
		datas.put("total", pager.getTotalRow());
		datas.put("rows", list);
		return datas.toString();
	}
	@RequestMapping("/picture")
	public String picture(HttpServletRequest request,String deviceNum,String openTime, ModelMap model)
			throws Exception {
		ServletContext servletContext = request.getServletContext();//获取ServletContext的对象 代表当前WEB应用

		FtpUtil.downfile(servletContext.getRealPath("/uploads/hlxx/"+deviceNum+"/"),"/"+deviceNum);
		model.addAttribute("pictureList",getFileList(servletContext.getRealPath("/uploads/hlxx/"+deviceNum+"/")));

		return "admin/opendoor/pList";
	}
	public static List<String> getFileList(String strPath) {
		List<String> filelist = new ArrayList<>();
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
		if (files != null) {
			int index=files.length;
			if(index>10)index=9;
			for (int i = 0; i < index; i++) {
				String fileName = files[i].getName();
				if (files[i].isDirectory()) { // 判断是文件还是文件夹
					getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
				} else if (fileName.endsWith("jpg")) { // 判断文件名是否以.avi结尾
					System.out.println(files[i].getPath().split("uploads/")[1]);
					filelist.add(files[i].getPath().split("uploads/")[1]);
				} else {
					continue;
				}
			}

		}
		return filelist;
	}
}
