package com.icomment.icomment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icomment.icomment.domain.ERol;
import com.icomment.icomment.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long>{

	Optional<Rol> findByName(ERol name);
}
