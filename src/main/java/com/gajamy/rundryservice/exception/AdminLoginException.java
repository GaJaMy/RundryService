package com.gajamy.rundryservice.exception;

public class AdminLoginException extends RuntimeException{
	public AdminLoginException(String contents) {
		super(contents);
	}
}
