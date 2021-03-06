package com.dream.api;

import com.dream.brick.admin.bean.Syslog;
import com.dream.brick.admin.bean.User;
import com.dream.brick.admin.dao.IUserDao;
import com.dream.brick.equipment.bean.*;
import com.dream.brick.equipment.dao.*;
import com.dream.brick.listener.BasicData;
import com.dream.util.FormatDate;
import com.dream.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: ApiAction.java
 * @Description: 对外接口
 * @date 2018-02-12 下午4:55
 */
@Controller
@Scope("prototype")

@RequestMapping("/api")
public class ApiAction {
	@Resource
	private IKeyssDao ikeyssDao;
	@Resource
	private ILocksDao ilocksDao;
	@Resource
	private IKeysListDao IKeysListDao;

	@Resource
	private IUserDao userDao;
	@Resource
	private IOpenLogDao openLogDao;

	@Resource
	private CollectorDao collectorDao;
	@Resource
	private IOpenDoorDao openDoorDao;
	@RequestMapping("keys")
	@ResponseBody
	public String addKeyss(@ModelAttribute Keyss keyss){
		try {
			keyss.setKeyssDate(FormatDate.getYMdHHmmss());
			ikeyssDao.save(keyss);
			return  StringUtil.jsonValue("1", "添加成功！");
		}catch (Exception e){
			return  StringUtil.jsonValue("0", "添加失败！");

		}
	}
	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description:  插入统计
	 * @date         2018-02-22 18:00:56
	 * @params
	 * @return
	 * @throws
	 */
	@RequestMapping("keys/list")
	@ResponseBody
	public String keyssList(@ModelAttribute KeyssList keyssList){
		try {
			keyssList.setCreateTime(FormatDate.getYMdHHmmss());
			IKeysListDao.save(keyssList);
			return  StringUtil.jsonValue("1", "添加成功！");
		}catch (Exception e){
			e.printStackTrace();
			return  StringUtil.jsonValue("0", "添加失败！");

		}
	}
	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description: 添加设备日志
	 * @date         2018-03-01 09:58:25
	 * @params
	 * @return
	 * @throws
	 */
	@RequestMapping("/logs")
	@ResponseBody
	public void apiLog(@ModelAttribute Syslog syslog){
		String[] arr = syslog.getUsername().split("");
		String userId=arr[1]+arr[3]+arr[5]+arr[7];
		User user=userDao.findUserById(userId);
		if(user!=null){
			syslog.setUsername(userDao.findUserById(userId).getUsername());
		}
		syslog.setCreateTime(FormatDate.getYMdHHmmss());
		BasicData.saveSyslog(syslog);
	}
	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description: 添加开锁日志
	 * @date         2018-03-01 09:58:25
	 * @params
	 * @return
	 * @throws
	 */
	@RequestMapping("open/log")
	@ResponseBody
	public void apiLog(@ModelAttribute OpenLog openlog){
		try {
			openlog.setCreateTime(FormatDate.getYMdHHmmss());
			if(StringUtils.isNotEmpty(openlog.getLockNum())){
				Map<String,String> map=new HashMap<>();
				char[] bankArray = openlog.getLockNum().toCharArray();
				String bankString = "";
				for(int i=0;i<bankArray.length;i++){
					if(i%4==0 && i>0){
						bankString +="-";
					}
					bankString += bankArray[i];
				}
				map.put("lockCode",bankString);
				List<Locks> list= ilocksDao.findLocks(map);
				if(list.size()>0){
					openlog.setLockName(list.get(0).getLockNum());
				}
			}
			openLogDao.save(openlog);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description: 添加开锁日志
	 * @date         2018-03-01 09:58:25
	 * @params
	 * @return
	 * @throws
	 */
	@RequestMapping("open/door")
	@ResponseBody
	public void apiDoor(@ModelAttribute OpenDoor openDoor){
		openDoor.setCreateTime(FormatDate.getYMdHHmmss());
		try {
			OpenDoor door=openDoorDao.find(OpenDoor.class,openDoor.getId());
			if(door==null){
				Map<String,String>  params=new HashMap<>();
				params.put("ccode",openDoor.getRtuId());
				List<Collector> collectorList=collectorDao.findCollector(params);
				if(collectorList.size()>0) {
					openDoor.setDeviceName(collectorList.get(0).getDis().getName());
				}
				openDoorDao.save(openDoor);
			}
		}catch (Exception e){

		}
	}

	public static void main(String[] args) {
		char[] bankArray = "00410001001200210001000000000000".toCharArray();
		String bankString = "";
		for(int i=0;i<bankArray.length;i++){
			if(i%4==0 && i>0){
				bankString +="-";
			}
			bankString += bankArray[i];
		}
		System.out.println(bankString);
	}
}
