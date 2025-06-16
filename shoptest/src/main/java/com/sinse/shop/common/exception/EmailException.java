package com.sinse.shop.common.exception;

public class EmailException extends RuntimeException{
	
	public EmailException(String msg) {
		super(msg);
	}
	public EmailException(Throwable e) {
		super(e);
	}
	public EmailException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
