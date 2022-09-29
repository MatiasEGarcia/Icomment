package com.icomment.icomment.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icomment.icomment.domain.User;
import com.icomment.icomment.service.UserService;

@RestController
@RequestMapping("/userC")
@CrossOrigin("*")
public class UserC {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value= "/admin/listAll") 
	public ResponseEntity<Collection<User>> listAll() throws Exception{
		return new ResponseEntity<Collection<User>>(userService.getAll(),HttpStatus.OK);
	}
}
