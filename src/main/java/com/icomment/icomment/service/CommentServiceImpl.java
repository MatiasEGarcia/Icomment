package com.icomment.icomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.icomment.icomment.dao.CommentDao;
import com.icomment.icomment.domain.Comment;
import com.icomment.icomment.gservice.GenericServiceImpl;

@Service
public class CommentServiceImpl extends GenericServiceImpl<Comment, Long> implements CommentService{
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public JpaRepository<Comment, Long> getDao() {
		return commentDao;
	}
}


