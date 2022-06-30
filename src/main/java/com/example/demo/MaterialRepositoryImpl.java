package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MaterialRepositoryImpl implements MaterialRepository {

	@Autowired
	EntityManager em;

	public List<Material> findByMenuCode(int menuCode) {
		String sql = "SELECT md.menu_detail_code,md.num,ma.name "
				+ "FROM menu_detail md JOIN material ma ON md.material_code = ma.material_code "
				+ "WHERE md.menu_code = :menu_code "
				+ "ORDER BY md.menu_detail_code";
		Query query = em.createNativeQuery(sql, Material.class);
		query.setParameter("menu_code", menuCode);
		@SuppressWarnings("unchecked")
		List<Material> menuMaterials = query.getResultList();
		return menuMaterials;
	}


//	public List<Item> findByCategoryCode(Integer categoryCode) {
//		String sql = "SELECT * FROM item WHERE category_code = :category_code ORDER BY code ASC";
//		Query query = em.createNativeQuery(sql, Item.class);
//		query.setParameter("category_code", categoryCode);
//		@SuppressWarnings("unchecked")
//		List<Item> list = query.getResultList();
//		return list;
//	}
//
//	public Item findByCode(int code) {
//		String sql = "SELECT * FROM item WHERE code = :code";
//		Query query = em.createNativeQuery(sql, Item.class);
//		query.setParameter("code", code);
//		Item item = (Item) query.getSingleResult();
//		return item;
//	}
//
//	public Item findById(int code) {
//		String sql = "SELECT * FROM item WHERE code = :code";
//		Query query = em.createNativeQuery(sql, Item.class);
//		query.setParameter("code", code);
//		Item item = (Item) query.getSingleResult();
//		return item;
//	}
//
//	public List<Item> findRank() {
//		String sql = "SELECT count(o.num),i.name  FROM ordered_detail o " + "JOIN item i ON o.item_code = i.code "
//				+ "GROUP BY i.code ORDER BY count DESC";
//		Query query = em.createNativeQuery(sql, ItemRank.class);
//		@SuppressWarnings("unchecked")
//		List<Item> list = query.getResultList();
//		return list;
//	}
//
//	public String andCheck(int andCheck) {
//		if (andCheck > 0) {
//			return "AND ";
//		} else {
//			return "";
//		}
//	}

}
