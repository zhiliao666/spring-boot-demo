
### 解决redis缓存连接超时等异常可以穿透到数据库返回数据

### 前言

最近项目中通过观察后台异常日志经常会发现Could not get a resource from the pool和Read timed out异常，缓存使用的是redis，两个错误异常的的具体原因后边会抽时间专门写一篇文章来说明，今天从另外一个角度来解决这个问题，就是当出现这种异常的时候穿透到数据库返回数据

### 如何使用

#### 自定义一个MyRedisCacheWrapper类如下：

```
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

```

#### 自定义CustomRedisCacheManager如下：

```
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
```
#### 最后redisConfig中引用对应的自定义CustomRedisCacheManager如下：

```
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
	
	@Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }
    
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<?, ?> redisTemplate) {
        CustomRedisCacheManager redisCacheManager = new CustomRedisCacheManager(redisTemplate);
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        @SuppressWarnings({ "unchecked", "rawtypes" })
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
```

### 演示结果：

对应的演示效果大家可以代码下载下来跑一下

### 技术要点以及注意事项

技术要点：默认的RedisCache的操作是没有对应的异常捕获的，这边MyRedisCacheWrapper主要是增加对应的异常捕获处理
注意事项：对于线上业务不是很确定能否因为异常穿透到数据需要具体分析，不能因为某一个业务缓存异常穿透到数据库引起整个数据库瘫痪

### 更多优质文章欢迎关注小编公众

![一个懒惰的程序员](https://github.com/zhiliao666/spring-boot-starter/blob/master/spring-boot-starter-exception-monitor/qrcode_for_gh_d88d06cbce83_258%20.jpg)

