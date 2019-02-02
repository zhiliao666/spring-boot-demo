package com.zhiliao.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhiliao.demo.Sender;

@RestController
@RequestMapping("/testApi")
public class ControllerTest {
	
	@Autowired
    private Sender sender;
	
	@Autowired
    private Sender sender1;
	
	@RequestMapping("/sendDirect")
    public String sendDirect(String queue,String msg){
		sender.sendDirect(queue, msg);
        return msg;
    }
	
	@RequestMapping("/sendTopic")
    public String sendTopic(String msg){
        return msg;
    }
	
	@RequestMapping("/sendFanout")
    public String sendFanout(String msg){
		sender.sendFanout(msg);
        return msg;
    }
}
