package com.icomment.icomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	@Override
	@Transactional(readOnly = true)
	public Boolean existsByUsername(String username) throws Exception {
		try {
			return userDao.existsByUsername(username);
        } catch (DataAccessException e) {
        	e.printStackTrace();
            throw new Exception("Database Error");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Unknown Error");
        }
	}


	@Override
	@Transactional(readOnly = true)
	public Boolean existsByEmail(String email) throws Exception {
		try {
			return userDao.existsByEmail(email);
        } catch (DataAccessException e) {
        	e.printStackTrace();
            throw new Exception("Database Error");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Unknown Error");
        }
	}

}
