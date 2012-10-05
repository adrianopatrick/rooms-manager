package br.edu.fanor.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.edu.fanor.dao.GenericDAO;
import br.edu.fanor.exceptions.ValidacaoException;


@Stateless
public class GenericService<T> implements Serializable{

	private static final long serialVersionUID = 3235565028980347621L;

	@EJB
	protected GenericDAO<T> dao;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public T save(T t) throws ValidacaoException {
		validarInsert(t);
		return dao.insert(t);
	}

	//TODO: avaliar necessidade desse método
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public T saveOrUpdate(T t) throws ValidacaoException {
		validar(t);
		return dao.insertOrUpdate(t);
	}
	
//	public List<Livro> pesquisaCodigo(String t) throws ValidacaoException {
//		return dao.pesquisa(t);
//	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public T update(T t) throws ValidacaoException {	
		return update(t, false);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public T update(T t, Boolean initializeDependencies)
			throws ValidacaoException {
		validarUpdate(t);
		return dao.update(t, initializeDependencies);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void delete(T t) throws ValidacaoException {
		validarDelete(t);
		dao.delete(t);
	}

	public List<T> findAll(Class<T> modelClass, Boolean initializeDependecies){
		return dao.findAll(modelClass, initializeDependecies);
	}
	
	public List<T> findAll(Class<T> modelClass){
		return dao.findAll(modelClass, false);
	}
	
	public List<T> example(T bean, Boolean initializeDependecies){
		return dao.example(bean, initializeDependecies);
	}
	
	public T findById(Class<T> modelClass, Object id){
		return dao.findById(modelClass, id, false);
	}
	
	public T findById(Class<T> modelClass, Object id, Boolean initializeDependencies){
		return dao.findById(modelClass, id, initializeDependencies);
	}
	
	
	@SuppressWarnings("unchecked")
	public T initializeDependecies(T t){
		return (T) dao.initializeDependecies(t);
	}

	public List<T> findAll(T modelClass, Integer startPage, Integer maxPage, String orderField){
		return dao.findAll(modelClass, startPage, maxPage, orderField, false);
	} 
	
	public List<T> findAll(T modelClass, Integer startPage, Integer maxPage, String orderField, Boolean initializeDependecies){
		return dao.findAll(modelClass, startPage, maxPage, orderField, initializeDependecies);
	} 
	
	public Long count(T modelClass){
		return dao.count(modelClass);
	}

	/**
	 * Validação antes da inserção
	 * @param t
	 * @throws ValidacaoException
	 */
	protected void validarInsert(T t) throws ValidacaoException {
		validar(t);
	}

	/**
	 * Validação antes de um update
	 * @param t
	 * @throws ValidacaoException
	 */
	protected void validarUpdate(T t) throws ValidacaoException {
		validar(t);
	}

	/**
	 * Validação antes do delete
	 * @param t
	 * @throws ValidacaoException
	 */
	protected void validarDelete(T t) throws ValidacaoException {}

	/**
	 * Esse método deve ser sobrescrito caso haja necessidade de validação server-side
	 * @param t
	 * @throws ValidacaoException
	 */
	protected void validar(T t) throws ValidacaoException {}
}
