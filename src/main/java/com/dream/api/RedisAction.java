package com.dream.api;

import com.alibaba.fastjson.JSON;
import com.dream.brick.listener.SessionData;
import com.dream.socket.entity.AuthModel;
import com.dream.socket.entity.DataProtocol;
import com.dream.socket.entity.JsonDataProtocol;
import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.util.AppMsg;
import com.dream.util.RedisTemplateUtil;
import com.dream.util.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/redis")
public class RedisAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;


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
           authModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
        }
        String macAddess=keys[2].replace(":","");
        macAddess="00000000000000000000"+macAddess;

        DataProtocol dataProtocol=new DataProtocol(new byte[]{00,01}, ByteUtil.hexToBytes(macAddess),ByteUtil.hexToBytes(authModel));
      /*  JsonDataProtocol jsonDataProtocol=new JsonDataProtocol();
        jsonDataProtocol.setCollectorId(keys[0]);
        jsonDataProtocol.setContent(dataProtocol.toString());
        jsonDataProtocol.setDataType("client");
        redisTemplateUtil.setList("lanya-lite", JSON.toJSONString(jsonDataProtocol));*/
        System.out.println(dataProtocol.toString());
        redisTemplateUtil.setList("lanya-lite", dataProtocol.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = redisTemplateUtil.get(dataProtocol.toString());
        if (o == null) {
            return   StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        } else {
            return  StringUtil.jsonValue("1", o.toString());
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
