package com.icomment.icomment.gservice;

import java.util.List;

public interface GenericService <T,ID>{

	T save (T entity) throws Exception;
	
	void saveAll (List<T> entity) throws Exception;
	
	int delete(ID id) throws Exception;
	
	T get(ID id) throws Exception;
	
	List<T> getAll() throws Exception;
}
