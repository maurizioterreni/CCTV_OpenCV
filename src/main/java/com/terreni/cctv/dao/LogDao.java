package com.terreni.cctv.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.terreni.cctv.model.Log;

@Stateless
public class LogDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Log log){
		entityManager.persist(log);
	}
	
	public Log findById(Long logId){
		return entityManager.find(Log.class, logId);
	}
	
	public void update(Log log) {
		Log persisted = findById(log.getId());
		persisted.setMex(log.getMex());
		persisted.setTime(log.getTime());
		entityManager.flush();
	}

	public void delete(Long logId) {
		Log log = findById(logId);
		entityManager.remove(log);
		entityManager.flush();
	}
	
}
