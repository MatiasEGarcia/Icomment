package com.icomment.icomment.service;

import com.icomment.icomment.domain.InvalidateToken;
import com.icomment.icomment.gservice.GenericService;

public interface InvalidateTokenService extends GenericService<InvalidateToken, Long>{

	Boolean existsBytoken(String token)throws Exception;
}
