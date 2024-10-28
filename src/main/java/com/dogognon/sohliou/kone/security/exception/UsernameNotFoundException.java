package com.dogognon.sohliou.kone.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String phone;
	private String email;
	
	public UsernameNotFoundException( String phone, String email) {
		super(String.format("User not found with phone : " + phone+" Or with "+email));
		this.phone = phone;
		this.email = email;
		
	}
	

}
