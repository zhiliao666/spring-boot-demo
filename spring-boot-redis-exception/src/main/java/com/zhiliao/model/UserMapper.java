package com.zhiliao.model;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

public interface UserMapper {
	
	@Cacheable(value = "usercache") 
	public List<User> getUsers(User param);
}
