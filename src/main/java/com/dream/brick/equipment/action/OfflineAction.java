package com.dream.brick.equipment.action;

import com.alibaba.fastjson.JSON;
import com.dream.brick.admin.bean.User;
import com.dream.brick.equipment.bean.AuthLog;
import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.IAuthLogDao;
import com.dream.brick.listener.SessionData;
import com.dream.serial.ReadSerialPortData;
import com.dream.socket.entity.AuthModel;
import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.util.*;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: OfflineAction.java
 * @Description:
 * @date 2018-03-30 上午10:09
 */
@Controller
@Scope("prototype")
@RequestMapping("/offline")
public class OfflineAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;
    @Resource
    private IAuthLogDao authLogDao;
    //在线授权
    @RequestMapping("/prViewAuth")
    public String prViewAuth( ModelMap model) {
        return "admin/offline/authList";
    }
    /**
    * class_name:
    * param:
    * describe: 获取可用串口
    * creat_user: taller
    * creat_date: 2018/3/30
    * creat_time: 15:24
    **/
    @ResponseBody
    @RequestMapping("/serial")
    public String serial() {
        ReadSerialPortData serialPortData=new ReadSerialPortData();
        List<String> list=serialPortData.findPort();
        List<JSONObject> jsonObjects=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            JSONObject json=new JSONObject();
            json.put("id", list.get(i));
            json.put("name", list.get(i));
            jsonObjects.add(json);
        }
        return JSON.toJSONString(list);
    }
    @ResponseBody
    @RequestMapping("/read")
    public String readAuth(String serial,String T,String userId,String lockNum,String deptId,String startDate,String endDate,String macAddress) {
        String  authModel=null;
        ReadSerialPortData serialPortData=new ReadSerialPortData();
        try {
            //钥匙绑定
            if("7".equals(T)){
                authModel=new AuthModel(new byte[]{7}, AuthModel.toBingKeyData(14, ByteUtil.hexStrToByteArray(ByteUtil.addZeroForNum(userId,8))), Constants.KEY).toString();
            }else if("12".equals(T)){
                //钥匙校时
                authModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
            }else if("1".equals(T)) {
                //获取门锁信息  key=0000000002,1,DF:98,
                authModel = new AuthModel(new byte[]{1}, AuthModel.toData(1, 1), Constants.KEY).toString();
            }else if("2".equals(T)){
                redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
                //初始化锁      key=0000000002,2,DF:98:deptId,lockCode
                if(StringUtils.isEmpty(lockNum)){
                    Object value = redisTemplateUtil.get("lanya-lock-client");
                    if (value == null) {
                        lockNum = "00" + deptId;
                        lockNum = StringUtil.addZeroForNum(lockNum, 16);
                        redisTemplateUtil.set("lanya-lock-client", lockNum);
                    } else {
                        lockNum = String.valueOf(Long.parseLong(value.toString()) + 1);
                        redisTemplateUtil.set("lanya-lock-client", lockNum);
                    }
                }
                authModel=new AuthModel(new byte[]{2},AuthModel.toLockData(32,lockNum),Constants.KEY).toString();
            }else if("5".equals(T)){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                AuthLog authLog = new AuthLog();
                authLog.setId(uuid);
                authLog.setCreateTime(FormatDate.getYMdHHmmss());
                authLog.setAuthStartTime(startDate);
                authLog.setAuthName("离线授权!");
                User user=new User();
                user.setId(userId);
                authLog.setUser(user);
                authLog.setAuthType(2);
                authLog.setAuthKeysId("");
                authLog.setAuthKeys("");
                authLog.setAuthLocks(lockNum);
                authLog.setAuthLocksId(lockNum);
                authLog.setAuthEndTime(endDate);
                authLogDao.save(authLog);
                macAddress="EE:56:8A:87:D9:5F";
                authModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKey(ByteUtil.hexStrToByteArray(ByteUtil.addZeroForNum(userId,8)),lockNum,macAddress,startDate,endDate),Constants.LOCK_KEY).toString();
            }
            serialPortData.setResponseString("");
            serialPortData.selectPort(serial);
            serialPortData.write(authModel);
            serialPortData.startRead(3);
        }catch (Exception e){
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
        try {
            if("1".equals(T))
            Thread.sleep(3000);
            else
                Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
        try{
            String responseStr= ResponseSocketUtil.V(serialPortData.getResponseString());
            responseStr=responseStr.replace("*","");
            return  StringUtil.jsonValue("1",responseStr);
        }catch (Exception e){
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
    }
    @RequestMapping("/prViewAreaOfflineAuth")
    public String prViewAreaOfflineAuth(){
        return "admin/offline/authAreaList";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute AuthLog authLog,String serial) {
        String message = "";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            authLog.setId(uuid);
            authLog.setAuthStatus("0");
            authLog.setCreateTime(FormatDate.getYMdHHmmss());
            authLogDao.save(authLog);
            message = StringUtil.jsonValue("1", "添加授权任务成功！");
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        auth(authLog,uuid,serial);
        return message;
    }
    private void auth(AuthLog authLog,String uuid,String serial) {
        if (StringUtils.isNotEmpty(authLog.getAuthLocksId())) {
            String[] locks = authLog.getAuthLocksId().split(",");
            for (int i = 0; i < locks.length; i++) {
                if (StringUtils.isNotEmpty(locks[i])) {
                    String authModel = new AuthModel(new byte[]{5}, AuthModel.AuthorizationKey(ByteUtil.hexStrToByteArray(ByteUtil.addZeroForNum(authLog.getUser().getId(), 8)), locks[i], authLog.getAuthKeysId(), FormatDate.dateParse(authLog.getAuthStartTime()), FormatDate.dateParse(authLog.getAuthEndTime())), Constants.LOCK_KEY).toString();//
                    auth(authModel,serial,uuid);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    private String  auth(String authModel,String serial,String uuid){
        ReadSerialPortData serialPortData=new ReadSerialPortData();
        serialPortData.setResponseString("");
        serialPortData.selectPort(serial);
        serialPortData.write(authModel);
        serialPortData.startRead(2);
        try{
            Thread.sleep(2000);
            String responseStr= ResponseSocketUtil.V(serialPortData.getResponseString());
            responseStr=responseStr.replace("*","");
            if(responseStr.indexOf("授权成功")>-1){
                System.out.println("第二次授权开始");
                AuthLog authLog=authLogDao.find(AuthLog.class,uuid);
                authLog.setAuthStatus("1");
                authLogDao.update(authLog);
            }
            return  StringUtil.jsonValue("1",responseStr);
        }catch (Exception e){
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
    }
}
