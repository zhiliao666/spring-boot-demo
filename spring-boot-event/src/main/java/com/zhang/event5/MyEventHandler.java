package com.zhang.event5;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler {
	
	@EventListener
	public void myListener(MyApplicationEvent5 event){
		System.out.println(event.getClass().getName() + "被监听中。。。。");
	}
}
