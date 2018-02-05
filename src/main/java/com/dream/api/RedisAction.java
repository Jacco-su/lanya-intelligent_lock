package com.dream.api;

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

@Controller
@RequestMapping("/redis")
public class RedisAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;


    @RequestMapping(value = "/get", method = {RequestMethod.POST})
    @ResponseBody
    public String get(String key) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.setList("lanya-lite", key);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = redisTemplateUtil.get(key);
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
