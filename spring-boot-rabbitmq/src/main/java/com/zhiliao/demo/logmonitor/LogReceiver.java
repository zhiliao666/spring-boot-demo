package com.zhiliao.demo.logmonitor;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;

/**
 * 日志接收者
 *
 * @author zhangqh
 * @date 2019年1月11日
 */
@Component
public class LogReceiver {
	
//	@RabbitListener(queues = "log_info")
	@RabbitListener(bindings={
			@QueueBinding(
					value = @Queue(value = "log_info"),
					exchange=@Exchange(value="log_exchange",type=ExchangeTypes.TOPIC),
					key={"log.info.*","log.all.*"}
					)})
	public void infoProcess(String msg) {
		System.out.println("收到info类型的日志为 : " + msg);
	}
	
//	@RabbitListener(queues = "log_warn")
	@RabbitListener(bindings={
			@QueueBinding(
					value = @Queue(value = "log_warn"),
					exchange=@Exchange(value="log_exchange",type=ExchangeTypes.TOPIC),
					key={"log.warn.*","log.all.*"}
					)})
	public void warnProcess(String msg) {
		System.out.println("收到warn类型的日志为 : " + msg);
	}
	
//	@RabbitListener(queues = "log_error")
	@RabbitListener(bindings={
			@QueueBinding(
					value = @Queue(value = "log_error"),
					exchange=@Exchange(value="log_exchange",type=ExchangeTypes.TOPIC),
					key={"log.error.*","log.all.*"}
					)})
	public void errorProcess(String msg) {
		System.out.println("收到error类型的日志为 : " + msg);
	}
	
	/**   Fanout   Exchange   */
	
	@RabbitListener(queues = "#{autoDeleteQueue0.name}")
    public void receiver0(String str) {
        System.out.println("receiver0++++++++++:" + str);
    }

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receiver1(String str) {
        System.out.println("receiver1++++++++++:" + str);
    }
    
    /** header */
    
    @RabbitListener(queues = "header-queue")
    public void receiveHeaders(byte[] msg) {
    	System.out.println("Headers receiver1++++++++++:" + new String(msg));
    }

}
