package com.zhiliao.demo.logmonitor;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiliao.demo.Sender;

/**
 * 日志生产者
 *
 * @author zhangqh
 * @date 2019年1月11日
 */
@Component
public class LogProducer {
	
	@Autowired
	private Sender sender;
	
	public void sendInfoLog(String msg){
		sender.sendDirect("log_info", msg);
	}
	
	public void sendWarnLog(String msg){
		sender.sendDirect("log_warn", msg);
	}
	
	public void sendErrorLog(String msg){
		sender.sendDirect("log_error", msg);
	}
	
	public void sendTopicAllLog(String msg){
		sender.sendTopic("log_exchange", "log.all.msg", msg);
	}
	
	public void sendTopicInfoLog(String msg){
		sender.sendTopic("log_exchange", "log.info.*", msg);
	}
	
	public void sendTopicWarnLog(String msg){
		sender.sendTopic("log_exchange", "log.warn.*", msg);
	}
	
	public void sendTopicErrorLog(String msg){
		sender.sendTopic("log_exchange", "log.error.*", msg);
	}
	
	
	public void sendFanoutMsg(String msg){
		sender.sendFanout("fanout-exchange", msg);
	}
	
	
	public void sendHeadersMsg(String msg) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("h1", "v3");
        messageProperties.setHeader("h2", "v2");
        Message message = new Message(msg.getBytes(), messageProperties);
        sender.sendHeaders("header-exchange", message);
    }
}
