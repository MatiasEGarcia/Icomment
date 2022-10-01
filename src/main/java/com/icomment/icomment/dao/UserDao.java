package com.icomment.icomment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icomment.icomment.domain.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
