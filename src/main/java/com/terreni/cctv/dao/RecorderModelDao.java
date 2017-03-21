package com.terreni.cctv.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.terreni.cctv.model.RecorderModel;

@Stateless
public class RecorderModelDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(RecorderModel recorderModel){
		entityManager.persist(recorderModel);
	}
	
	public RecorderModel findById(Long recorderModelId){
		return entityManager.find(RecorderModel.class, recorderModelId);
	}
	
	public void update(RecorderModel recorderModel) {
		RecorderModel persisted = findById(recorderModel.getId());
		persisted.setPath(recorderModel.getPath());
		persisted.setTime(recorderModel.getTime());
		entityManager.flush();
	}

	public void delete(Long recorderModelId) {
		RecorderModel recorderModel = findById(recorderModelId);
		entityManager.remove(recorderModel);
		entityManager.flush();
	}
}
