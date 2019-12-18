package com.zhiliao.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class RabbitConfig {


	/**
	 * 延迟交换机
	 */
	public static final String DELAY_EXCHANGE_NAME = "delay.exchange";

	/**
	 * 延迟队列
	 */
	public static final String DELAY_QUEUE1_NAME = "delay.queue1";
	public static final String DELAY_QUEUE2_NAME = "delay.queue2";
	public static final String DELAY_QUEUE3_NAME = "delay.queue3";

	/**
	 * 延迟队列路由
	 */
	public static final String DELAY_QUEUE1_ROUTING_KEY = "delay.queue1.routingkey";
	public static final String DELAY_QUEUE2_ROUTING_KEY = "delay.queue2.routingkey";
	public static final String DELAY_QUEUE3_ROUTING_KEY = "delay.queue3.routingkey";

	/**
	 * 死信交换机
	 */
	public static final String DEAD_LETTER_EXCHANGE = "delay.deadletter.exchange";

	/**
	 * 死信队列
	 */
	public static final String DEAD_LETTER_QUEUE1_NAME = "delay.deadletter.queue1";
	public static final String DEAD_LETTER_QUEUE2_NAME = "delay.deadletter.queue2";
	public static final String DEAD_LETTER_QUEUE3_NAME = "delay.deadletter.queue3";

	/**
	 * 死信队列路由
	 */
	public static final String DEAD_LETTER_QUEUE1_ROUTING_KEY = "delay.deadletter.delay1.routingkey";
	public static final String DEAD_LETTER_QUEUE2_ROUTING_KEY = "delay.deadletter.delay2.routingkey";
	public static final String DEAD_LETTER_QUEUE3_ROUTING_KEY = "delay.deadletter.delay3.routingkey";




	/**
	 * 声明延迟交换机
	 * @return
	 */
	@Bean("delayExchange")
	public DirectExchange delayExchange(){
		return new DirectExchange(DELAY_EXCHANGE_NAME);
	}

	/**
	 * 声明死信交换机
	 * @return
	 */
	@Bean("deadLetterExchange")
	public DirectExchange deadLetterExchange(){
		return new DirectExchange(DEAD_LETTER_EXCHANGE);
	}


	/**
	 * 声明一个延迟10秒的队列
	 * 并绑定到对应的死信交换机
	 * @return
	 */
	@Bean("delayQueue1")
	public Queue delayQueue1(){
		Map<String, Object> args = new HashMap<>(3);
		args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);// 声明当前队列绑定的死信交换机
		args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE1_ROUTING_KEY);// 声明当前队列的死信路由key
		args.put("x-message-ttl", 10000);// 声明队列的TTL
		return QueueBuilder.durable(DELAY_QUEUE1_NAME).withArguments(args).build();
	}

	/**
	 * 声明一个延迟60秒的队列
	 * 并绑定到对应的死信交换机
	 * @return
	 */
	@Bean("delayQueue2")
	public Queue delayQueue2(){
		Map<String, Object> args = new HashMap<>(3);
		args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);// 声明当前队列绑定的死信交换机
		args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE2_ROUTING_KEY);// 声明当前队列的死信路由key
		args.put("x-message-ttl", 60000);// 声明队列的TTL
		return QueueBuilder.durable(DELAY_QUEUE2_NAME).withArguments(args).build();
	}


	/**
	 * 声明一个延迟无指定ttl的队列,ttl交由消息设置
	 * 并绑定到对应的死信交换机
	 * @return
	 */
	@Bean("delayQueue3")
	public Queue delayQueue3(){
		Map<String, Object> args = new HashMap<>(3);
		args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);// 声明当前队列绑定的死信交换机
		args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE3_ROUTING_KEY);// 声明当前队列的死信路由key
		return QueueBuilder.durable(DELAY_QUEUE3_NAME).withArguments(args).build();
	}


	/**
	 * 声明死信队列1 用户接受处理延时10秒的消息
	 * @return
	 */
	@Bean("deadLetterQueue1")
	public Queue deadLetterQueue1(){
		return new Queue(DEAD_LETTER_QUEUE1_NAME);
	}

	/**
	 * 声明死信队列1 用户接受处理延时60秒的消息
	 * @return
	 */
	@Bean("deadLetterQueue2")
	public Queue deadLetterQueue2(){
		return new Queue(DEAD_LETTER_QUEUE2_NAME);
	}


	/**
	 * 声明死信队列3 用户接受处理延时60秒的消息
	 * @return
	 */
	@Bean("deadLetterQueue3")
	public Queue deadLetterQueue3(){
		return new Queue(DEAD_LETTER_QUEUE3_NAME);
	}



	// 声明延迟队列绑定关系

	@Bean
	public Binding delayBinding1(@Qualifier("delayQueue1") Queue queue,
								 @Qualifier("delayExchange") DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE1_ROUTING_KEY);
	}

	@Bean
	public Binding delayBinding2(@Qualifier("delayQueue2") Queue queue,
								 @Qualifier("delayExchange") DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE2_ROUTING_KEY);
	}

	@Bean
	public Binding delayBinding3(@Qualifier("delayQueue3") Queue queue,
								 @Qualifier("delayExchange") DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE3_ROUTING_KEY);
	}



	// 声明死信队列绑定关系

	@Bean
	public Binding deadLetterBinding1(@Qualifier("deadLetterQueue1") Queue queue,
									  @Qualifier("deadLetterExchange") DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE1_ROUTING_KEY);
	}


	@Bean
	public Binding deadLetterBinding2(@Qualifier("deadLetterQueue2") Queue queue,
									  @Qualifier("deadLetterExchange") DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE2_ROUTING_KEY);
	}


	@Bean
	public Binding deadLetterBinding3(@Qualifier("deadLetterQueue3") Queue queue,
									  @Qualifier("deadLetterExchange") DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE3_ROUTING_KEY);
	}



	/** 下边是基于延迟队列插件的方式实现的定义  只需要定义一个队列，根据消息TTL处理对应业务逻辑 */



	public static final String DELAYED_QUEUE_NAME = "delay.one.queue";
	public static final String DELAYED_EXCHANGE_NAME = "delay.one.exchange";
	public static final String DELAYED_ROUTING_KEY = "delay.one.routingkey";



	@Bean
	public CustomExchange customExchange() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-delayed-type", "direct");
		return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
	}



}
