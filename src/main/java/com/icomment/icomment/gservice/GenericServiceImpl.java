package com.icomment.icomment.gservice;

import java.util.Collection;
import java.util.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icomment.icomment.exception.DaoException;

@Service
public abstract class GenericServiceImpl<T,ID> implements GenericService<T, ID>{

	@Override
	@Transactional
	public T save(T entity) throws Exception {
		try {
			return getDao().save(entity);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}
	
	@Override
	@Transactional
	public void saveAll(Collection<T> entity) throws Exception {
		try {
			getDao().saveAll(entity);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}

	@Override
	@Transactional
	public int delete(ID id) throws Exception{
		int commentDeleted = 0;
		try {
			if(this.get(id) != null) {
				getDao().deleteById(id);
				commentDeleted = 1;
			}
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
		return commentDeleted;
	}
	
	@Override
	@Transactional
	public void deleteAll(Collection<T> entity) throws Exception {
		try {
			 getDao().deleteAll(entity);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}

	@Override
	@Transactional(readOnly = true)
	public T get(ID id) throws Exception{
		Optional<T> obj;
		try {
			obj = getDao().findById(id);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<T> getAll() throws Exception {
		try {
			return getDao().findAll();
        } catch (DataAccessException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
	}
	//Those class than extends from this abstract class will edit this method with their repository
	public abstract JpaRepository<T,ID> getDao();

}
