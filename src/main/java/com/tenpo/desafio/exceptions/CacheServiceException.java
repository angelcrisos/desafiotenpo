package com.tenpo.desafio.exceptions;

import lombok.Setter;

public class CacheServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Setter
	private String message;

	public CacheServiceException(String message) {
		super(message);
	}
	
	
	

}
