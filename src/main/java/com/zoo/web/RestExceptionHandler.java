package com.zoo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Resource does not exist, check your input data")
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public void notFoundExceptionHandler(DataAccessResourceFailureException ex) {
		LOGGER.debug("404: " + ex.getMessage());
	}

}
