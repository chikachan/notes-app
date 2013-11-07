package tabitabi.picco.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

abstract class AbstractJPADataService<T, ID extends Serializable> implements
		DataService<T,ID> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> type;

	@SuppressWarnings("unchecked")
	AbstractJPADataService() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();		
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public T create(final T t) {
		em.persist(t);
		return t;
	}

	@Override
	public void delete(final ID id) {
		em.remove(em.getReference(type, id));
	}

	@Override
	public T find(final ID id) {
		return em.find(type, id);
	}

	
	public Collection<T>findAll(){
		return Collections.emptyList();
	}
	
	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}
}
