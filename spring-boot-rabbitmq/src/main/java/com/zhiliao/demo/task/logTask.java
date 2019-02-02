package com.zhiliao.demo.task;

import com.zhiliao.demo.logmonitor.LogProducer;
/**
 * 日志发送任务类
 *
 * @author zhangqh
 * @date 2019年1月17日
 */
public class logTask implements Runnable{
	
	private LogProducer logProducer;
	
	private int taskNum;
	
	private int logType;// 0 All 1 info 2 warn 3 error
	
	public logTask(LogProducer logProducer,int taskNum,int logType){
		this.logProducer = logProducer;
		this.taskNum = taskNum;
		this.logType = logType;
	}

	@Override
	public void run() {
		if(logType == 1){
			logProducer.sendInfoLog("info日志="+taskNum);
		}else if(logType == 2){
			logProducer.sendWarnLog("warn日志="+taskNum);
		}else if(logType == 3){
			logProducer.sendErrorLog("error日志="+taskNum);
		}
	}

}
