package com.dream.brick.equipment.action;

import com.dream.brick.equipment.bean.OpenDoor;
import com.dream.brick.equipment.dao.IOpenDoorDao;
import com.dream.framework.dao.Pager;
import com.dream.util.FormatDate;
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
    public String list(int page, int rows, String deptId, Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<OpenDoor> list = openDoorDao.findDoorLogList("", pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }

    @RequestMapping("/picture")
    public String picture(HttpServletRequest request, String deviceNum, String openTime, ModelMap model)
            throws Exception {
        ServletContext servletContext = request.getServletContext();//获取ServletContext的对象 代表当前WEB应用
        String time = openTime.substring(0, 10).replace("-", "");
        String path = servletContext.getRealPath("/uploads/hlxx/" + deviceNum + "/" + time + "/");
        System.out.println("path:" + path);
        FtpUtil.downfile(path, "/" + deviceNum, time);
        openTime = FormatDate.dateMinuteParse(openTime);
        List<String> filelist = getFileList(path);
        List<String> list = new ArrayList<>();
        //System.out.println("getFileList:"+filelist);
        String timeOpen = openTime;
        int count = 0;
        for (int i = 0; i < filelist.size(); i++) {
            if (filelist.get(i).indexOf(openTime) > -1) {
                if (count < 10) {
                    count++;
                    list.add(filelist.get(i));
                }
            }
        }
        if (count < 10) {
            timeOpen = FormatDate.addDateMinut(openTime, 1);
            for (int i = 0; i < filelist.size(); i++) {
                if (filelist.get(i).indexOf(timeOpen) > -1) {
                    if (count < 10) {
                        count++;
                        list.add(filelist.get(i));
                    }
                }
            }
        }
        if (count < 10) {
            timeOpen = FormatDate.addDateMinut(openTime, 2);
            for (int i = 0; i < filelist.size(); i++) {
                if (filelist.get(i).indexOf(timeOpen) > -1) {
                    if (count < 10) {
                        count++;
                        list.add(filelist.get(i));
                    }
                }
            }
        }
        if (count < 10) {
            timeOpen = FormatDate.addDateMinut(openTime, 3);
            for (int i = 0; i < filelist.size(); i++) {
                if (filelist.get(i).indexOf(timeOpen) > -1) {
                    if (count < 10) {
                        count++;
                        list.add(filelist.get(i));
                    }
                }
            }
        }
        if (count < 10) {
            timeOpen = FormatDate.addDateMinut(openTime, -1);
            for (int i = 0; i < filelist.size(); i++) {
                if (filelist.get(i).indexOf(timeOpen) > -1) {
                    if (count < 10) {
                        count++;
                        list.add(filelist.get(i));
                    }
                }
            }
        }
        if (count < 10) {
            timeOpen = FormatDate.addDateMinut(openTime, -2);
            for (int i = 0; i < filelist.size(); i++) {
                if (filelist.get(i).indexOf(timeOpen) > -1) {
                    if (count < 10) {
                        count++;
                        list.add(filelist.get(i));
                    }
                }
            }
        }
        if (count < 10) {
            timeOpen = FormatDate.addDateMinut(openTime, -3);
            for (int i = 0; i < filelist.size(); i++) {
                if (filelist.get(i).indexOf(timeOpen) > -1) {
                    if (count < 10) {
                        count++;
                        list.add(filelist.get(i));
                    }
                }
            }
        }
        System.out.println(timeOpen + "opentime");
        System.out.println("list" + list);
        model.addAttribute("pictureList", list);
        return "admin/opendoor/pList";
    }

    public static List<String> getFileList(String strPath) {
        List<String> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("jpg")) { // 判断文件名是否以.avi结尾
                    filelist.add(files[i].getPath().split("uploads")[1]);
                } else {
                    continue;
                }
            }

        }
        /*Collections.sort(filelist,new Comparator<String>() {
            @Override
			public int compare(String o1, String o2) {
				if(o1 == null || o2 == null){
					return -1;
				}
				if(o1.length() > o2.length()){
					return -1;
				}
				if(o1.length() < o2.length()){
					return 1;
				}
				if(o1.compareTo(o2) > 0){
					return -1;
				}
				if(o1.compareTo(o2) < 0){
					return 1;
				}
				if(o1.compareTo(o2) == 0){
					return 0;
				}
				return 0;
			}
		});*/
        return filelist;
    }

    public static void main(String[] args) {
        System.out.println("2016-08-20".substring(0, 10));
        List<String> list = new ArrayList<>();
        System.out.println(list.size());
        /*List<String > list=new ArrayList<>();
		list.add("hlxx/00000005/192.168.1.64_01_20180329140546219_ALARM_INPUT.jpg");
		list.add("hlxx/00000005/192.168.1.64_01_20180329140548339_ALARM_INPUT.jpg");
		list.add("hlxx/00000005/192.168.1.64_01_20180329140550888_ALARM_INPUT.jpg");
		list.add("hlxx/00000005/192.168.1.64_01_20180329140602584_ALARM_INPUT.jpg");
		list.add("hlxx/00000005/192.168.1.64_01_20180329140606095_ALARM_INPUT.jpg");
		Collections.sort(list,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1 == null || o2 == null){
					return -1;
				}
				if(o1.length() > o2.length()){
					return -1;
				}
				if(o1.length() < o2.length()){
					return 1;
				}
				if(o1.compareTo(o2) > 0){
					return -1;
				}
				if(o1.compareTo(o2) < 0){
					return 1;
				}
				if(o1.compareTo(o2) == 0){
					return 0;
				}
				return 0;
			}
		});
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}*/
    }
}
