package com.icomment.icomment.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.icomment.icomment.dao.InvalidateTokenDao;
import com.icomment.icomment.domain.InvalidateToken;
import com.icomment.icomment.gservice.GenericServiceImpl;

public class InvalidateTokenServiceImpl extends GenericServiceImpl<InvalidateToken, Long> implements InvalidateTokenService{

	@Autowired
	private InvalidateTokenDao invalidateTokenDao;
	
	@Override
	public JpaRepository<InvalidateToken, Long> getDao() {
		return invalidateTokenDao;
	}



}
