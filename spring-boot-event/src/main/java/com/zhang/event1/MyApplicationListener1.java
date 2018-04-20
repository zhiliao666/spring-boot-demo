package com.zhang.event1;

import org.springframework.context.ApplicationListener;

public class MyApplicationListener1 implements ApplicationListener<MyApplicationEvent1> {

	@Override
	public void onApplicationEvent(MyApplicationEvent1 event) {
		System.out.println(event.getClass().getName() + "被监听中。。。。");
	}

}
