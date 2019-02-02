package com.zhiliao.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
	
//	@Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);             //开启手动 ack
//        return factory;
//    }
	/**
	 * 新建一个名为log_info的
	 * @return
	 */
	@Bean
	public Queue infoQueue() {
		return new Queue("log_info");
	}
	
	@Bean
	public Queue warnQueue() {
		return new Queue("log_warn");
	}
	
	@Bean
	public Queue errorQueue() {
		return new Queue("log_error");
	}
	
	@Bean
	public Queue allQueue() {
		return new Queue("log_all");
	}
	
	@Bean
	public TopicExchange topicExchange(){
		return new TopicExchange("log_exchange");
	}
	
	/** 创建日志对应的路由绑定   */
	
	@Bean
	public Binding allexchangeBinding(){
		return BindingBuilder.bind(allQueue()).to(topicExchange()).with("log.all.*");
	}
	
	@Bean
	public Binding errorexchangeBinding(){
		return BindingBuilder.bind(errorQueue()).to(topicExchange()).with("log.error.*");
	}
	
	@Bean
	public Binding warnexchangeBinding(){
		return BindingBuilder.bind(warnQueue()).to(topicExchange()).with("log.warn.*");
	}
	
	@Bean
	public Binding infoexchangeBinding(){
		return BindingBuilder.bind(infoQueue()).to(topicExchange()).with("log.info.*");
	}
	
	//  fanout模式
	
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout-exchange");
	}
	
	@Bean
    public Queue autoDeleteQueue0() {
        return new AnonymousQueue();
    }
	@Bean
    public Binding binding0(FanoutExchange fanoutExchange, Queue autoDeleteQueue0) {
        return BindingBuilder.bind(autoDeleteQueue0).to(fanoutExchange);
    }
	
	@Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }
	@Bean
    public Binding binding1(FanoutExchange fanoutExchange, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
    }
	
	
	// Headers模式
	
    @Bean
    public HeadersExchange headersExchage(){
        return new HeadersExchange("header-exchange");
    }
    
    @Bean
    public Queue headerQueue() {
        return new Queue("header-queue");
    }
    
    @Bean
    public Binding headerBinding() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("h1", "v1");
        map.put("h2", "v2");
        return BindingBuilder.bind(headerQueue()).to(headersExchage()).whereAll(map).match();
    }

}
