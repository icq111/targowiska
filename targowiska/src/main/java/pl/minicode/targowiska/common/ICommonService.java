package pl.minicode.targowiska.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

public abstract interface ICommonService<T> {

	List<T> findAll();
	
	T findById(Long id) throws EntityNotFoundException;
	
	T save(T entity);
	
	T update(T entity);
	
	T delete(T entity);
	
	EntityNotFoundException throwEntityNotFoundException(Long id);
}
