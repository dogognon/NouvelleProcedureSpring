package com.dogognon.sohliou.kone.security.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dogognon.sohliou.kone.security.exception.BadRequestException;
import com.dogognon.sohliou.kone.security.exception.InternalServerErrorException;
import com.dogognon.sohliou.kone.security.exception.MyFileNotFoundException;
import com.dogognon.sohliou.kone.security.exception.ResourceNotFoundException;
import com.dogognon.sohliou.kone.security.exception.model.ErrorDetails;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<?> InternalServerErrorException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> BadRequestException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MyFileNotFoundException.class)
	public ResponseEntity<?> MyFileNotFoundException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	/*@ExceptionHandler(MyBadCredentialsException.class)
	public ResponseEntity<?> MyBadCredentialsException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}*/
	
	/*@ExceptionHandler(MyBadJWTException.class)
	public ResponseEntity<?> MyBadJWTException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}*/
	
	
	
	/*@ExceptionHandler(OAuth2AuthenticationProcessingException.class)
	public ResponseEntity<?> MyOAuth2AuthenticationProcessingException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}*/
	
	
}
