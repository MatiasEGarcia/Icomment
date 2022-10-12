package com.icomment.icomment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icomment.icomment.domain.InvalidateToken;

@Repository
public interface InvalidateTokenDao extends JpaRepository<InvalidateToken, Long>{

	Boolean existsByToken(String token);
}
