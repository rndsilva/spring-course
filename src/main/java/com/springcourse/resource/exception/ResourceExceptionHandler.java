package com.springcourse.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springcourse.exception.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNorFoundException(NotFoundException ex ) {
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date()); 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		
		//String defaultMessage= ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		
		List<String> errors = new ArrayList<String>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {
			errors.add(error.getDefaultMessage());
			
		});
		
		String defaultMessage = "Invalide fields";
		
		ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(),defaultMessage, new Date(), errors);
		
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}  
	

	/*ADD Chat CPG*/
/*	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    StringBuilder sb = new StringBuilder();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        sb.append(error.getField())
	          .append(": ")
	          .append(error.getDefaultMessage())
	          .append("; ");
	    });

	    ApiError error = new ApiError(
	        HttpStatus.BAD_REQUEST.value(),
	        sb.toString(),
	        new Date()
	    );

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	*/
	/*ADD Chat CPG*/
}
