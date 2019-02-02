package com.zhiliao.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void sendDirect(String queue,String msg) {
		this.rabbitTemplate.convertAndSend(queue, msg);
	}
	
	public void sendTopic(String exchange,String routingkey,String msg) {
		this.rabbitTemplate.convertAndSend(exchange,routingkey,msg);
	}
	
	public void sendFanout(String exchange,String msg) {
		this.rabbitTemplate.convertAndSend(exchange,"",msg);
	}
	
	public void sendFanout(String msg) {
		this.rabbitTemplate.convertAndSend("amq.fanout","fanout.msg", msg);
	}
	
	public void sendHeaders(String exchange,Object msg) {
		this.rabbitTemplate.convertAndSend(exchange, "", msg);
	}
	
}
