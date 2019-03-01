package com.zhiliao.config.annotation;
import org.springframework.web.bind.annotation.Mapping;
import java.lang.annotation.*;
 
/**
 * 版本控制注解
 * 注：可以作用在类上也可以在方法头上
 * 
 * @author zhangqh
 * @date 2019年3月1日
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    /**
     * 标识版本号
     * @return
     */
    int value();

}