package com.zhang.event4;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件，继承ApplicationEvent抽象类
 *
 * @author zhangqh
 * @date 2018年4月19日
 */
public class MyApplicationEvent4 extends ApplicationEvent {

	private static final long serialVersionUID = 2488171893578077741L;

	public MyApplicationEvent4(Object source) {
		super(source);
	}

}
