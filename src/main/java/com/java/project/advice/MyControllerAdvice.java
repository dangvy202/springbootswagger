package com.java.project.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.java.project.exception.ErrorEmptyInputExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ErrorEmptyInputExceptionHandler.class)
	public ResponseEntity<String> exceptionEmptyInput(ErrorEmptyInputExceptionHandler emptyException){
		return new ResponseEntity<String>("Input Empty Please Check Input",HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> exceptionElementsNotFound(NoSuchElementException exceptionElementNotFound){
		return new ResponseEntity<String>("Id Wrong , Please Check Again",HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Page Not Found Check Request",HttpStatus.NOT_FOUND);
	}	
}
