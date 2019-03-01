package com.zhiliao.api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhiliao.config.annotation.ApiVersion;


@RestController
@RequestMapping("/{version}")
public class ControllerTest {
	
	@ApiVersion(1)
	@RequestMapping("/hello")
    public String hello(String msg){
        return "hello v1: "+msg;
    }
	
	@RequestMapping("/other")
    public String other(String msg){
        return "hello v1: "+msg;
    }
	
	@ApiVersion(2)
	@RequestMapping("/hello")
    public String hello2(String msg){
        return "hello v2: "+msg;
    }
	
}
