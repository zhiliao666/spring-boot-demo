package com.zhiliao.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhiliao.demo.logmonitor.LogProducer;
import com.zhiliao.demo.task.logTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMonitorTest {
	
	@Autowired
	private LogProducer logProducer;
	
	@Test
	public void logTest(){
		logProducer.sendInfoLog("info日志");
		logProducer.sendWarnLog("warn日志");
		logProducer.sendErrorLog("error日志");
	}
	
	@Test
	public void logTopicTest(){
		logProducer.sendTopicAllLog("all日志");
//		logProducer.sendTopicWarnLog("warn日志");
//		logProducer.sendTopicInfoLog("info日志");
//		logProducer.sendTopicErrorLog("error日志");
	}
	
	@Test
	public void msgFanoutTest(){
		logProducer.sendFanoutMsg("fanout消息");
	}
	
	
	@Test
	public void msgHeaderTest(){
		logProducer.sendHeadersMsg("header msg.....");
	}
	
	
	@Test
	public void logTaskTest(){
		try {
//			 ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, 1500, 200, TimeUnit.MILLISECONDS,
//	                 new ArrayBlockingQueue<Runnable>(1000000));
			 
			ExecutorService executor = Executors.newCachedThreadPool();  
	          
	         for(int i=0;i<15000;i++){
	        	 logTask myTask = new logTask(logProducer,i,1);
	             executor.execute(myTask);
//	             Thread.sleep(100);
//	             System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
//	             executor.getQueue().size()+"，已执行完别的任务数目："+executor.getCompletedTaskCount());
	         }
         
			Thread.sleep(5000);
//         executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
