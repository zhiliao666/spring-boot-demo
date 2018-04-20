package com.zhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.zhang.event1.MyApplicationEvent1;
import com.zhang.event1.MyApplicationListener1;
import com.zhang.event2.MyApplicationEvent2;
import com.zhang.event3.MyApplicationEvent3;
import com.zhang.event4.MyApplicationEvent4;
import com.zhang.event5.MyApplicationEvent5;

@SpringBootApplication
public class EventApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(EventApplication.class, args);
		
		// 第一种方式 手动把事件监听方法 加入到spring容器管理
        //把监听器加入到SpringApplication中
        context.addApplicationListener(new MyApplicationListener1());
        //发布事件
        context.publishEvent(new MyApplicationEvent1(new Object()));
        
        // 第二种方式 直接在MyApplicationListener上增加@Component注解 交给spring容器托管
        context.publishEvent(new MyApplicationEvent2(new Object()));
        
        // 第三种方式 直接在application.properties增加监听器的路径如下：
        // context.listener.classes=com.zhang.event3.MyApplicationListener3
        context.publishEvent(new MyApplicationEvent3(new Object()));
        
        // 第四种方式 在META-INF/spring.factories文件中增加配置如下
        // org.springframework.context.ApplicationListene=com.zhang.event4.MyApplicationListener4
        context.publishEvent(new MyApplicationEvent4(new Object()));
        
        // 第五种方式 通过@EventListener注解方式实现
        context.publishEvent(new MyApplicationEvent5(new Object()));
        
        context.close();
	}
}
