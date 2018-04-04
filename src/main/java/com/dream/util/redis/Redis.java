package com.dream.util.redis;

import redis.clients.jedis.Jedis;

public class Redis {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.3.95", 6379);
        jedis.auth("hw@redis_321");
        jedis.set("XX","PPP");
        System.out.println(jedis.get("XX"));
       // System.out.println(jedis.get));
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行..:");
        System.out.println("明明白白");
        }
}
