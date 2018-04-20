package com.zhang.event5;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件，继承ApplicationEvent抽象类
 *
 * @author zhangqh
 * @date 2018年4月19日
 */
public class MyApplicationEvent5 extends ApplicationEvent {

	private static final long serialVersionUID = 2488171893578077741L;

	public MyApplicationEvent5(Object source) {
		super(source);
	}

}
