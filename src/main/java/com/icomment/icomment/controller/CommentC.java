package com.icomment.icomment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icomment.icomment.domain.Comment;
import com.icomment.icomment.service.CommentService;


@RestController
@RequestMapping("/commentC")
@CrossOrigin("*")
public class CommentC {

	@Autowired
	private CommentService commentS;
	
	@GetMapping(value= "/admin/listAll") 
	public ResponseEntity<Iterable<Comment>> listAll() throws Exception{
		return new ResponseEntity<Iterable<Comment>>(commentS.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value= "/admin/findById/{id}")
	public ResponseEntity<Comment> find(@PathVariable Long id) throws Exception{
		Comment comment = commentS.get(id);
		if(comment != null) {
			return new ResponseEntity<Comment>(comment,HttpStatus.OK);
		}else {
			return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Comment> save(@RequestBody Comment comment) throws Exception{
		return new ResponseEntity<Comment>(commentS.save(comment),HttpStatus.OK);
	}
	
	@GetMapping(value= "/delete/{id}")
	public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception{
		if(commentS.delete(id) == 1) {  //if it is equal to 1 , it is because a comment was found and deleted
			return new ResponseEntity<Long>(id,HttpStatus.OK);
		}else {
			return new ResponseEntity<Long>(id,HttpStatus.NO_CONTENT);
		}
	
	}
	
	
	
	
}
