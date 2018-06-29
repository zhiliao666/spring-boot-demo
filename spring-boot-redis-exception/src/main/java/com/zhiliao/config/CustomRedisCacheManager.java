package com.zhiliao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;

/**
 * 自定义缓存RedisCacheManager
 *
 * @author zhangqh
 * @date 2018年6月29日
 */
public class CustomRedisCacheManager extends RedisCacheManager {

	private static Logger logger = LoggerFactory.getLogger(CustomRedisCacheManager.class);

    public CustomRedisCacheManager(RedisOperations<?, ?> redisOperations) {
        super(redisOperations);
    }

    @Override
    public Cache getCache(String name) {
        return new MyRedisCacheWrapper(super.getCache(name));
    }


}
