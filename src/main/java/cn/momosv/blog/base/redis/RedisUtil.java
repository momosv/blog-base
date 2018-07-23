package cn.momosv.blog.base.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
  public  static RedisTemplate redisTemplate;

    private static long l = 30;//过期时长

    @Autowired
    public  void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    public static void set(Object K, Object V){
        redisTemplate.opsForValue().set(K,V);
    }

    public static void set(Object K,Object V,long offset){
        redisTemplate.opsForValue().set(K,V,offset);
    }

    public static void set(Object K, Object V, long l, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(K,V,l,timeUnit);
    }
    public static void cacheSet(Object K, Object V){
        redisTemplate.opsForValue().set(K,V,l,TimeUnit.MINUTES);
    }

    public static Object get(Object K){
       return redisTemplate.opsForValue().get(K);
    }

}
