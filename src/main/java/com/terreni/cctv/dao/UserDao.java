package com.terreni.cctv.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.terreni.cctv.model.User;

@Stateless
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(User user){
		entityManager.persist(user);
	}
	
	public User findById(Long userId){
		return entityManager.find(User.class, userId);
	}
	
	public void update(User user) {
		User persisted = findById(user.getId());
		persisted.setPassword(user.getPassword());
		persisted.setPassword(user.getUsername());
		entityManager.flush();
	}

	public void delete(Long userId) {
		User user = findById(userId);
		entityManager.remove(user);
		entityManager.flush();
	}
}
