package com.icomment.icomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icomment.icomment.dao.UserDao;
import com.icomment.icomment.domain.User;
import com.icomment.icomment.exception.DaoException;
import com.icomment.icomment.gservice.GenericServiceImpl;

@Service("userDetailsService")
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService,UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public JpaRepository<User, Long> getDao() {
		return userDao;
	}


	@Override
	@Transactional(readOnly = true)
	public User getByUsername(String username) throws Exception {
		User user;
        try {
            user = userDao.findByUsername(username);
        } catch (DataAccessException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
	}

	
	@Override
	@Transactional(readOnly = true)
	public Boolean existsByUsername(String username) throws Exception {
		try {
			return userDao.existsByUsername(username);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}


	@Override
	@Transactional(readOnly = true)
	public Boolean existsByEmail(String email) throws Exception {
		try {
			return userDao.existsByEmail(email);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}


	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
        try {
            user = userDao.findByUsername(username);
        } catch (DataAccessException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
	}



	

}
