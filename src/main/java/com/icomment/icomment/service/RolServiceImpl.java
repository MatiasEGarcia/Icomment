package com.icomment.icomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.icomment.icomment.dao.RolDao;
import com.icomment.icomment.domain.Rol;
import com.icomment.icomment.gservice.GenericServiceImpl;

public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements RolService {

	@Autowired
	private RolDao rolDao;
	
	@Override
	public JpaRepository<Rol, Long> getDao() {
		return rolDao;
	}

}
