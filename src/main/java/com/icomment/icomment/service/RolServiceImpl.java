package com.icomment.icomment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.icomment.icomment.dao.RolDao;
import com.icomment.icomment.domain.ERol;
import com.icomment.icomment.domain.Rol;
import com.icomment.icomment.gservice.GenericServiceImpl;

public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements RolService {

	@Autowired
	private RolDao rolDao;
	
	@Override
	public JpaRepository<Rol, Long> getDao() {
		return rolDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Rol> findByName(ERol name) throws Exception {
		try {
			return rolDao.findByName(name);
        } catch (DataAccessException e) {
        	e.printStackTrace();
            throw new Exception("Database Error");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Unknown Error");
        }
	}
}
