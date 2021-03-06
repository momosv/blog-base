package cn.momosv.blog.base.redis.service.impl;

import cn.momosv.blog.base.redis.util.RedisUtils;
import cn.momosv.blog.base.redis.service.RedisUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class RedisUserServiceImpl implements RedisUserService {

    @Override
    public String set(String key) {
        System.out.println("momo");
        HashMap map = new HashMap();
        map.put(key,"wq");
        RedisUtils.cacheSet(key,map);
        return "插入缓存 "+key+" "+new Date().toString();
    }

    @Override
    public String get(String key) {
        HashMap<String,String> map = (HashMap) RedisUtils.get(key);
        return map.get(key);
    }
}
