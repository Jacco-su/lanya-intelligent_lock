package com.dream.api;

import com.alibaba.fastjson.JSON;
import com.dream.brick.equipment.bean.Authorization;
import com.dream.brick.equipment.dao.IAuthorizationDao;
import com.dream.brick.listener.SessionData;
import com.dream.socket.entity.AuthModel;
import com.dream.socket.entity.DataProtocol;
import com.dream.socket.entity.JsonDataProtocol;
import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
import com.dream.util.RedisTemplateUtil;
import com.dream.util.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/redis")
public class RedisAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;
    @Resource
    private IAuthorizationDao authorizationDao;

    @RequestMapping(value = "/get", method = {RequestMethod.POST})
    @ResponseBody
    public String get(String key,HttpServletRequest request) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        String [] keys=key.split(",");
        String  authModel=null;

        if("7".equals(keys[1])){
            ////采集器id:指令字:mac地址:钥匙mac地址
            authModel=new AuthModel(new byte[]{7}, AuthModel.toData(7,14), Constants.KEY).toString();
        }else if("12".equals(keys[1])){
            //给钥匙校时
            ////采集器id:指令字:控制器mac地址:钥匙mac地址
           authModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
        }else if("5".equals(keys[1])){
            //开始授权
            //采集器id:指令字:控制器mac地址:钥匙mac地址:锁识别号:开始日期:结束日期
            authModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKey(ByteUtil.hexStrToByteArray("01010101"),ByteUtil.hexStrToByteArray(keys[4]),keys[2],keys[5],keys[6]),Constants.LOCK_KEY).toString();//
        }else if("1".equals(keys[1])){
            //获取门锁信息  key=0000000002,1,DF:98,
            //采集器id:指令字:控制器mac地址
            authModel=new AuthModel(new byte[]{1},AuthModel.toData(1,1),Constants.KEY).toString();
        }else if("2".equals(keys[1])){
            //初始化锁      key=0000000002,2,DF:98,
           authModel=new AuthModel(new byte[]{2},AuthModel.toData(2,10),Constants.KEY).toString();
        }else if("13".equals(keys[1])){
            //获取钥匙Mac地址
            authModel = new AuthModel(new byte[]{13}).toString();
        }

        String macAddess=keys[2].replace(":","");
        macAddess="00000000000000000000"+macAddess;
        DataProtocol dataProtocol=new DataProtocol(new byte[]{00,01}, ByteUtil.hexToBytes(macAddess),ByteUtil.hexToBytes(authModel));
        JsonDataProtocol jsonDataProtocol=new JsonDataProtocol();
        jsonDataProtocol.setCollectorId(keys[0]);
        jsonDataProtocol.setContent(dataProtocol.toString());
        jsonDataProtocol.setDataType("client");
        System.out.println(dataProtocol.toString());
        String authKey=JSON.toJSONString(jsonDataProtocol)+";"+SessionData.getAdminId(request);
        for (int i = 0; i < 3; i++) {
            redisTemplateUtil.setList("lanya-lite", authKey); }
        try {
            Thread.sleep(10000);
            Object o = redisTemplateUtil.get(authKey);
            if (o == null) {
                return   StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
            } else {
                if("5".equals(keys[1])){
                    if("授权成功!".equals(o.toString())){
                        Authorization authorization=new Authorization();
                        authorization.setType("client");
                        authorization.setLocksid(keys[4]);
                        authorization.setKeyssid(keys[3]);
                        authorization.setAid(SessionData.getAdminId(request));
                        authorization.setUid(keys[7]);
                        authorization.setStarttime(FormatDate.getYMdHHmmss(keys[5]));
                        authorization.setEndtime(FormatDate.getYMdHHmmss(keys[6]));
                        authorization.setAdate(FormatDate.getYMdHHmmss());
                        authorizationDao.save(authorization);
                    }
                }
                return  StringUtil.jsonValue("1", o.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return  StringUtil.jsonValue("1", "连接失败!");
        }

    }

    @RequestMapping("/set")
    @ResponseBody
    public String set(String key, String value) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.set(key, value);
        return StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
    }
}
