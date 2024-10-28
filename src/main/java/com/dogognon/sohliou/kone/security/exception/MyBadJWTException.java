package com.dogognon.sohliou.kone.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyBadJWTException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyBadJWTException(String message) {
		super(message);
	}

	public MyBadJWTException(String message, Throwable cause) {
		super(message, cause);
	}
}
