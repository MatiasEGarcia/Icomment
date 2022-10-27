package com.icomment.icomment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.icomment.icomment.exception.DaoException;

@ControllerAdvice
public class ExceptionC {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionC.class);
	
	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> ExceptionHandler(Exception e) {
        LOGGER.error("There was some error: ",e.getMessage());
        return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = { DaoException.class })
    public ResponseEntity<Object> DaoExceptionHandler(DaoException e) {
        LOGGER.error("There was some error in the dao : ",e.getMessage());
        return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
