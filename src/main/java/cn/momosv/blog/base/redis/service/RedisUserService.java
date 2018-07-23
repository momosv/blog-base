package cn.momosv.blog.base.redis.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface RedisUserService {
    //@CachePut(value ="momou", key = "#p0")
    String set(String key);
    //@Cacheable(value ="momou",key = "#p0")
    String get(String key);
}
