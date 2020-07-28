package com.empresa.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.empresa.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice //anotation que permite interceptar possíveis exceções para este objeto dar o seu devido tratamento
public class ResourceExceptionHandler {
	
	//intercepta exceções do tipo ResourceNotFoundException para tratar nesse método
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ObjectNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; //error 404
		//StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}	
}
