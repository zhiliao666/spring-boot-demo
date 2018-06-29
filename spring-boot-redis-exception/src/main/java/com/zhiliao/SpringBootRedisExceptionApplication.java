package com.zhiliao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhiliao.model")
public class SpringBootRedisExceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisExceptionApplication.class, args);
	}
}
