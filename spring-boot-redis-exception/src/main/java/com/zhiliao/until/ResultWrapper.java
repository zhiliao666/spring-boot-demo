package com.zhiliao.until;

import java.io.Serializable;

/**
 * 返回值包装类，用于包装接口返回值及其它信息
 * <pre style="color:#F00;">
 * 	// 接口返回结果包装实例
 * 	ResultWrapper<T> wrapper = ... ;
 * 	// 判断接口调用是否成功
 *	if(wrapper.isSuccessful()) {
 * 	// 返回接口执行结果
 *		T result = wrapper.getResult() ;
 *	} else {
 * 	// 取得错误码(0表示未设错误码)、错误消息，作相应业务处理
 *		int errorCode = wrapper.getErrorCode() ;
 *		String errorMessage = wrapper.getErrorMsg() ;
 *	}
 * </pre>
 * @author	zhangqh
 * @param <T>
 */
public class ResultWrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected T result;						// 返回结果
	protected int errorCode;				// 错误码
	protected String errorMsg;				// 错误消息
	protected boolean successful = true;	// 是否成功，失败时应检查错误码作出相应处理
	
	public ResultWrapper() {
		super() ;
	}
	
	public ResultWrapper(T result) {
		this.result = result ;
	}
	
	/**
	 * 使用执行是否成功构造结果类，通常用于失败情况
	 * @param successful
	 */
	public ResultWrapper(boolean successful) {
		this.successful = successful ;
	}
	
	/**
	 * 构造方法错误码赋值
	 * @param errorCode
	 * @param errorMsg
	 */
	public ResultWrapper(int errorCode,String errorMsg) {
		this.successful = false ;
		this.errorCode = errorCode ;
		this.errorMsg = errorMsg ;
	}
	/**
	 * 自定义错误码
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public ResultWrapper<T> setError(int errorCode ,String errorMsg) {
		this.successful = false ;
		this.errorCode = errorCode ;
		this.errorMsg = errorMsg ;
		return this ;
	}

	public T getResult() {
		return result;
	}

	public ResultWrapper<T> setResult(T result) {
		this.result = result;
		return this ;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	public ResultWrapper<T> setErrorCode(int errorCode) {
		this.successful = false ;
		this.errorCode = errorCode ;
		return this ;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public ResultWrapper<T> setErrorMsg(String errorMsg) {
		this.successful = false ;
		this.errorMsg = errorMsg;
		return this ;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public ResultWrapper<T> setSuccessful(boolean successful) {
		this.successful = successful;
		return this ;
	}

}