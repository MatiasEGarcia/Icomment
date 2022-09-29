package com.icomment.icomment.service;

import java.util.Optional;

import com.icomment.icomment.domain.ERol;
import com.icomment.icomment.domain.Rol;
import com.icomment.icomment.gservice.GenericService;

public interface RolService extends GenericService<Rol, Long>{

	Optional<Rol> findByName(ERol name) throws Exception;
}
