package com.jeecg.p3.commonweixin.exception;

public class CommonweixinException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CommonweixinException(String message){
		super(message);
	}
	
	public CommonweixinException(Throwable cause)
	{
		super(cause);
	}
	
	public CommonweixinException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
