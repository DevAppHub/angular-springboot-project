package com.cts.admin.util;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception e, WebRequest webReq){
		String errorMessageDescription=e.getLocalizedMessage();
		if(e instanceof InvalidRequestException) {
			errorMessageDescription=e.getMessage();
		}
		if(errorMessageDescription==null) 
			errorMessageDescription=e.toString();
		Error errorMessage=new Error(errorMessageDescription, new Date());
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
