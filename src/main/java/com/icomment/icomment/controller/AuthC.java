package com.icomment.icomment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.icomment.icomment.domain.User;
import com.icomment.icomment.service.UserService;

@RestController
@RequestMapping("/AuthC")
@CrossOrigin("*")
public class AuthC {
	
	@Autowired
	private UserService userService;

	
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{
		if(userService.existsByUsername(user.getUsername())) {
			
		}
		if(userService.existsByEmail(user.getEmail())) {
			
		}
		
		
		return null;
	}
	
}
