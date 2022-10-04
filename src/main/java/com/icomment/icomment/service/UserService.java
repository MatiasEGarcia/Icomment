package com.icomment.icomment.service;

import com.icomment.icomment.domain.User;
import com.icomment.icomment.gservice.GenericService;

public interface UserService extends GenericService<User, Long>{
	
	User getByUsername(String username) throws Exception;

	Boolean existsByUsername(String username)throws Exception;

	Boolean existsByEmail(String email) throws Exception;
}
