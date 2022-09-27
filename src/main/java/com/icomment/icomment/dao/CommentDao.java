package com.icomment.icomment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icomment.icomment.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>{

}
	