package com.icomment.icomment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icomment.icomment.domain.User;
import com.icomment.icomment.payload.response.MessageResponse;
import com.icomment.icomment.service.UserService;

@RestController
@RequestMapping("/AuthC")
@CrossOrigin("*")
public class AuthC {
	
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{
		if(userService.existsByUsername(user.getUsername())) {
			 return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if(userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		}
		userService.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
}
