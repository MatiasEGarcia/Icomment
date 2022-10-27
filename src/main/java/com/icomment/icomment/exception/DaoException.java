package com.icomment.icomment.exception;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException{
	
	private static final long serialVersionUID = 1L;
	
	public DaoException(String msg) {
		super(msg);
	}
	
	public DaoException(DataAccessException e) {
		super(e.getMessage());
	}

}
