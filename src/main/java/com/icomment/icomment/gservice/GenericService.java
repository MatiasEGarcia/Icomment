package com.icomment.icomment.gservice;

import java.util.Collection;

public interface GenericService <T,ID>{

	T save (T entity) throws Exception;
	
	void saveAll (Collection<T> entity) throws Exception;
	
	int delete(ID id) throws Exception;
	
	void deleteAll (Collection<T> entity) throws Exception;
	
	T get(ID id) throws Exception;
	
	Collection<T> getAll() throws Exception;
}
