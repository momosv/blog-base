package cn.momosv.blog.base.redis.service.impl;

import cn.momosv.blog.base.redis.RedisUtil;
import cn.momosv.blog.base.redis.service.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCommand;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUserServiceImpl implements RedisUserService {

    @Override
    public String set(String key) {
        System.out.println("momo");
        HashMap map = new HashMap();
        map.put(key,"wq");
        RedisUtil.cacheSet(key,map);
        return "插入缓存 "+key+" "+new Date().toString();
    }

    @Override
    public String get(String key) {
        HashMap<String,String> map = (HashMap) RedisUtil.get(key);
        return map.get(key);
    }
}
