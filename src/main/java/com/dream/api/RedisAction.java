package com.dream.api;

import com.alibaba.fastjson.JSON;
import com.dream.brick.admin.bean.User;
import com.dream.brick.equipment.bean.AuthLog;
<<<<<<<HEAD
import com.dream.brick.equipment.bean.Authorization;
import com.dream.brick.equipment.bean.Locks;
=======
        >>>>>>>github/dev
import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.IAuthLogDao;
import com.dream.brick.equipment.dao.IAuthorizationDao;
import com.dream.brick.equipment.dao.ILocksDao;
import com.dream.brick.equipment.dao.QgdisDao;
import com.dream.brick.listener.SessionData;
import com.dream.socket.entity.AuthModel;
import com.dream.socket.entity.DataProtocol;
import com.dream.socket.entity.JsonDataProtocol;
import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
<<<<<<<HEAD
import com.dream.util.*;
import org.apache.commons.lang3.StringUtils;
=======
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
import com.dream.util.RedisTemplateUtil;
import com.dream.util.StringUtil;
>>>>>>>github/dev
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/redis")
public class RedisAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;
    @Resource
    private IAuthorizationDao authorizationDao;
    @Resource
    private QgdisDao disDao;
    @Resource
    private IAuthLogDao authLogDao;

    @Resource
    private ILocksDao ilocksDao;
    @RequestMapping(value = "/get", method = {RequestMethod.POST})
    @ResponseBody
    public String get(String serial,String key,String lockNum, HttpServletRequest request) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        String [] keys=key.split(",");
        String  authModel=null;
        //钥匙绑定
        if("7".equals(keys[1])){
            ////采集器id:指令字:mac地址:钥匙mac地址:用户id
            authModel=new AuthModel(new byte[]{7}, AuthModel.toBingKeyData(14,ByteUtil.hexStrToByteArray(ByteUtil.addZeroForNum(keys[4],8))), Constants.KEY).toString();
        }else if("12".equals(keys[1])){
            //给钥匙校时
            ////采集器id:指令字:控制器mac地址:钥匙mac地址
           authModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
        }else if("5".equals(keys[1])){
            //开始授权
            //采集器id:指令字:控制器mac地址:钥匙mac地址:锁识别号:开始日期:结束日期:用户id:站点ID
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            AuthLog authLog = new AuthLog();
            authLog.setId(uuid);
            authLog.setCreateTime(FormatDate.getYMdHHmmss());
            authLog.setAuthStartTime(keys[6]);
            authLog.setAuthName("在线授权!");
            User user=new User();
            user.setId(keys[7]);
            authLog.setUser(user);
            authLog.setAuthType(2);
            authLog.setAuthKeysId(keys[3]);
            authLog.setAuthKeys(keys[3]);
            authLog.setAuthLocks(keys[4]);
            authLog.setAuthLocksId(keys[4]);
            if(!"".equals(keys[6])) {
                authLog.setAuthEndTime(FormatDate.dateSdfHHmmssParse(keys[6]));
            }
            //authLogDao.save(authLog);
            authModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKeyX(keys[7],keys[4],keys[2],keys[5],keys[6],1,1),Constants.LOCK_KEY).toString();//
            SessionData.createSyslog(request,9, "用户离线授权");
        }else if("1".equals(keys[1])){
            //获取门锁信息  key=0000000002,1,DF:98,
            //采集器id:指令字:控制器mac地址
            authModel=new AuthModel(new byte[]{1},AuthModel.toData(1,1),Constants.KEY).toString();
            SessionData.createSyslog(request,9, "读取门锁信息");
        }else if("2".equals(keys[1])){
            //初始化锁      key=0000000002,2,DF:98:deptId,lockCode,disId
            if(StringUtils.isEmpty(lockNum)||"".equals(lockNum)) {
                String disId = keys[5];
                if (StringUtils.isEmpty(disId)) {
                    disId = "135";
                }
                lockNum=keys[3];
                Qgdis qgdis = disDao.find(Qgdis.class, disId);
                if(lockNum.length()<5) {
                    lockNum+= "-0001";
                }
                if(lockNum.length()<10) {
                    lockNum+= "-0001";
                }
                String str = String.format("%04d", qgdis.getOrderNum());
                lockNum = lockNum + "-" + str;
                if (qgdis.getLockCount() == null) {
                    lockNum += "-0001";
                } else {
                    str = String.format("%04d", qgdis.getLockCount() + 1);
                    lockNum += "-" + str;
                }
            }
            SessionData.createSyslog(request,9, "初始化锁");
            System.out.println(lockNum+":lockNum");
            authModel=new AuthModel(new byte[]{2},AuthModel.toLockDataByte(32,lockNum),Constants.KEY).toString();
        }else if("13".equals(keys[1])){
            //获取钥匙Mac地址
            authModel = new AuthModel(new byte[]{13}).toString();
        }
        //获取日志 by lwx
        else if("14".equals(keys[1])){
            authModel=new AuthModel(new byte[]{14}).toString();
        }
        String macAddess=keys[2].replace(":","");
        macAddess="00000000000000000000"+macAddess;
        DataProtocol dataProtocol=new DataProtocol(new byte[]{00,01}, ByteUtil.hexToBytes(macAddess),ByteUtil.hexToBytes(authModel));
        JsonDataProtocol jsonDataProtocol=new JsonDataProtocol();
        jsonDataProtocol.setCollectorId(keys[0]);
        jsonDataProtocol.setContent(dataProtocol.toString());
        jsonDataProtocol.setDataType("client");
        System.out.println(dataProtocol.toString());
        String authKey=JSON.toJSONString(jsonDataProtocol)+";"+request.getSession().getAttribute("userUUID");
        /*if("2".equals(keys[1])){
            redisTemplateUtil.setList("lanya-lite", authKey);
        }else {
            for (int i = 0; i < 3; i++) {
                redisTemplateUtil.setList("lanya-lite", authKey);
            }
        }*/
        redisTemplateUtil.setList("lanya-lite", authKey);
<<<<<<<HEAD
        int time=8500;
        Object o=null;
        while (time>0){
            o = redisTemplateUtil.get(authKey);
            if (o != null) {
                int t=o.toString().lastIndexOf("*")+1;
                if(keys[1].equals(t+"")){
                    //time=0;
                    redisTemplateUtil.set(authKey,"");
                    break;
                }
            }
            try {
                Thread.sleep(500);
                time=time-500;
            }catch (Exception e){
            }
        }
/*        try {
            Thread.sleep(time);
            *//*while (time>0){
=======
        int time = 15000;
        try {
            Thread.sleep(time - 2000);
            Object o = null;
            while (time > 0) {
>>>>>>> github/dev
                o = redisTemplateUtil.get(authKey);
                if (o == null) {
                    redisTemplateUtil.setList("lanya-lite", authKey);
<<<<<<< HEAD
                    time-=3000;
                    Thread.sleep(3000);
                }else{
                    int t=o.toString().lastIndexOf("*")+1;
                    if(keys[1].equals(t+"")){
                        break;
                    }else{
                        time-=3000;
                        o=null;
                        redisTemplateUtil.setList("lanya-lite", authKey);
                        Thread.sleep(3000);
=======
                    Thread.sleep(time - 1000);
                } else {
                    int t = o.toString().lastIndexOf("*") + 1;
                    if (keys[1].equals(t)) {
                        break;
                    } else {
                        o = null;
                        redisTemplateUtil.setList("lanya-lite", authKey);
                        Thread.sleep(time - 1000);
>>>>>>> github/dev
                    }
                }
            }*//*

        } catch (InterruptedException e) {
            e.printStackTrace();
            return  StringUtil.jsonValue("0", "连接失败!");
        }*/
        if (o == null) {
            return   StringUtil.jsonValue("0", "暂未获取到信息，请稍后尝试!");
        } else {
            int t=o.toString().lastIndexOf("*")+1;
            if(keys[1].equals(t+"")){
                if(o.toString().indexOf("门锁识别号")>-1){
                    Map<String,String> params =new HashMap<>();
                    params.put("lockCode",o.toString().split(";")[1]);
                    List<Locks> locksList= ilocksDao.findLocks(params);
                    if(locksList.size()>0){
                        Locks locks=locksList.get(0);
                        return  StringUtil.jsonValue("1", locks.getLockCode()+"门锁识别号已存在"+locks.getQgdis().getName());
                    }
                }
                return  StringUtil.jsonValue("1", o.toString().replace("*",""));
            }else{
                return  StringUtil.jsonValue("0", "暂未获取到信息，请稍后尝试!");
            }
        }
    }

    @RequestMapping("/set")
    @ResponseBody
    public String set(String key, String value) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.set(key, value);
        return StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
    }
    public static void main(String[] args) {

        System.out.println("*********塑封袋".replace("*",""));
        System.out.println(StringUtil.addZeroForNum("4110003",16));
        //34313130303033303030303030303031
        System.out.println(ByteUtil.bytesToHex("".getBytes()));
        System.out.println(ByteUtil.hexStr2Str("30343031303230323030303030303030"));
        System.out.println(new AuthModel(new byte[]{13}).toString());
    }
}
