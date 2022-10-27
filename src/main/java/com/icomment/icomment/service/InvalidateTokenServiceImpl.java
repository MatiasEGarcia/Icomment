package com.icomment.icomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icomment.icomment.dao.InvalidateTokenDao;
import com.icomment.icomment.domain.InvalidateToken;
import com.icomment.icomment.gservice.GenericServiceImpl;

@Service
public class InvalidateTokenServiceImpl extends GenericServiceImpl<InvalidateToken, Long> implements InvalidateTokenService{

	@Autowired
	private InvalidateTokenDao invalidateTokenDao;
	
	@Override
	public JpaRepository<InvalidateToken, Long> getDao() {
		return invalidateTokenDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean existsBytoken(String token) throws Exception {
		try {
			return invalidateTokenDao.existsByToken(token);
        } catch (DataAccessException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}



}
