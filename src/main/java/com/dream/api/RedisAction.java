package com.dream.api;

import com.dream.util.AppMsg;
import com.dream.util.RedisTemplateUtil;
import com.dream.util.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api")
public class RedisAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;

    @RequestMapping("/get")
    @ResponseBody
    public String get(String key) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        Object o = redisTemplateUtil.get(key);
        return String.valueOf(o);

    }

    @RequestMapping("/set")
    @ResponseBody
    public String set(String key, String value) {
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.set(key, value);
        return StringUtil.jsonValue("1", AppMsg.ADD_SUCCESS);
    }
}
