package com.zhang.event4;

import org.springframework.context.ApplicationListener;

public class MyApplicationListener4 implements ApplicationListener<MyApplicationEvent4> {

	@Override
	public void onApplicationEvent(MyApplicationEvent4 event) {
		System.out.println(event.getClass().getName() + "被监听中。。。。");
	}

}
