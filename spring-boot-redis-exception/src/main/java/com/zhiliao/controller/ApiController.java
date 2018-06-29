package com.zhiliao.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhiliao.model.User;
import com.zhiliao.model.UserMapper;

/**
 * 测试controller
 *
 * @author zhangqh
 * @date 2018年6月29日
 */
@RestController
@RequestMapping("api")
public class ApiController {
	
	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private UserMapper userService;
	
	
	@RequestMapping("users")
    public List<User> users(User param) {
        return userService.getUsers(param);
    }
	
	
}
