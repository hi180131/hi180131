package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MenuRepositoryImpl implements MenuRepository {

	@Autowired
	EntityManager em;

	public List<Menu> findAll() {
		String sql = "SELECT * FROM menu ORDER BY menu_code ASC";
		Query query = em.createNativeQuery(sql, Menu.class);
		@SuppressWarnings("unchecked")
		List<Menu> list = query.getResultList();
		return list;//
	}

	public List<Menu> findByCategoryCode(int categoryCode) {
		String sql = "SELECT * FROM menu WHERE category_code = :category_code ORDER BY menu_code ASC";
		Query query = em.createNativeQuery(sql, Menu.class);
		query.setParameter("category_code", categoryCode);
		@SuppressWarnings("unchecked")
		List<Menu> list = query.getResultList();
		return list;
	}

	public List<Menu> findByKeyword(String keyword) {
		String sql = "SELECT * FROM menu ";
		if (keyword != null && !keyword.equals("")) {
			sql += "WHERE name LIKE :name";
		}
		Query query = em.createNativeQuery(sql, Menu.class);
		if (keyword != null && !keyword.equals("")) {
			query.setParameter("name", "%" + keyword + "%");
		}
		@SuppressWarnings("unchecked")
		List<Menu> list = query.getResultList();
		return list;
	}


	public Menu findByCode(int menuCode) {
		String sql = "SELECT * FROM menu WHERE menu_code = :menu_code";
		Query query = em.createNativeQuery(sql, Menu.class);
		query.setParameter("menu_code", menuCode);
		Menu item = (Menu) query.getSingleResult();
		return item;
	}

	public List<Menu> findbyUserCode(int userCode){
		String sql = "SELECT * FROM menu WHERE user_code = :user_code";
		query.setParameter("user_code", userCode);
		@SuppressWarnings("unchecked")
		List<Menu> list = query.getResultList();
		return list;

	}

	public void deleteMenu(int userCode) {
		String sql = "DELETE FROM menu WHERE user_code = :user_code";
		Query query = em.createNativeQuery(sql);
		query.setParameter("user_code",userCode);
		query.executeUpdate();
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
