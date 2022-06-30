package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository  {
	
	@Autowired
	EntityManager em;
	
	public List<Category> findAll() {
		String sql = "SELECT * FROM category";
		Query query = em.createNativeQuery(sql, Category.class);
		@SuppressWarnings("unchecked")
		List<Category> list = query.getResultList();
		
		return list;
	}
}
