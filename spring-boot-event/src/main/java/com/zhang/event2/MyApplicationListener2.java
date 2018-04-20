package com.zhang.event2;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener2 implements ApplicationListener<MyApplicationEvent2> {

	@Override
	public void onApplicationEvent(MyApplicationEvent2 event) {
		System.out.println(event.getClass().getName() + "被监听中。。。。");
	}

}
