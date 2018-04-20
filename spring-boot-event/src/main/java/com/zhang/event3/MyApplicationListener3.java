package com.zhang.event3;

import org.springframework.context.ApplicationListener;

public class MyApplicationListener3 implements ApplicationListener<MyApplicationEvent3> {

	@Override
	public void onApplicationEvent(MyApplicationEvent3 event) {
		System.out.println(event.getClass().getName() + "被监听中。。。。");
	}

}
