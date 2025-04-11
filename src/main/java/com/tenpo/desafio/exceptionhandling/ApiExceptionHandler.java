package com.tenpo.desafio.exceptionhandling;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenpo.desafio.exceptions.CacheServiceException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException e) {
		Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(err -> err.getField(), err -> err.getDefaultMessage(), (msg1, msg2) -> msg1 ));

		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("errors", errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CacheServiceException.class)
	public ResponseEntity<?> handlerErrorCache(CacheServiceException e) {
		
		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.SERVICE_UNAVAILABLE);
		response.put("errors", e.getMessage());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
