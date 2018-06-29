package com.zhiliao.config;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.util.Assert;

/**
 * 自定义RedisCacheWrapper 增加try catch异常捕获
 *
 * @author zhangqh
 * @date 2018年6月29日
 */
public class MyRedisCacheWrapper implements Cache {
	
	private static Logger logger = LoggerFactory.getLogger(MyRedisCacheWrapper.class);

	private final Cache delegate;

    public MyRedisCacheWrapper(Cache redisCache) {
        Assert.notNull(redisCache, "delegate cache must not be null");
        this.delegate = redisCache;
    }

    @Override
    public String getName() {
        try {
            return delegate.getName();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public Object getNativeCache() {
        try {
            return delegate.getNativeCache();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public Cache.ValueWrapper get(Object key) {
        try {
            return delegate.get(key);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        try {
            return delegate.get(o, aClass);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        try {
            return delegate.get(o, callable);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public void put(Object key, Object value) {
        try {
            delegate.put(key, value);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        try {
            return delegate.putIfAbsent(o, o1);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public void evict(Object o) {
        try {
            delegate.evict(o);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void clear() {
        try {
            delegate.clear();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private <T> T handleException(Exception e) {
        logger.error("handleException", e);
        return null;
    }

}
