package com.icomment.icomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.icomment.icomment.dao.UserDao;
import com.icomment.icomment.domain.User;
import com.icomment.icomment.gservice.GenericServiceImpl;

@Service("userDetailsService")
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public JpaRepository<User, Long> getDao() {
		return userDao;
	}

}
