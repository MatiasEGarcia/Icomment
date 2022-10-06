package com.icomment.icomment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icomment.icomment.domain.InvalidateToken;

public interface InvalidateTokenDao extends JpaRepository<InvalidateToken, Long>{

	Boolean existsByToken(String token);
}
