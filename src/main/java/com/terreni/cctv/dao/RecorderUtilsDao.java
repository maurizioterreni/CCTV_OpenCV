package com.terreni.cctv.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.terreni.cctv.model.RecorderUtils;

@Stateless
public class RecorderUtilsDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(RecorderUtils recorderUtils){
		entityManager.persist(recorderUtils);
	}
	
	public RecorderUtils findById(Long recorderUtilsId){
		return entityManager.find(RecorderUtils.class, recorderUtilsId);
	}
	
	public void update(RecorderUtils recorderUtils) {
		RecorderUtils persisted = findById(recorderUtils.getId());
		persisted.setFormat(recorderUtils.getFormat());
		persisted.setFps(recorderUtils.getFps());
		persisted.setHeight(recorderUtils.getHeight());
		persisted.setWidth(recorderUtils.getWidth());
		persisted.setPath(recorderUtils.getPath());
		entityManager.flush();
	}

	public void delete(Long recorderUtilsId) {
		RecorderUtils recorderUtils = findById(recorderUtilsId);
		entityManager.remove(recorderUtils);
		entityManager.flush();
	}
}
