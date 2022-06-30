package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository {
	List<Menu> findAll();
	List<Menu> findByCategoryCode(int categoryCode);
	List<Menu> findByKeyword(String keyword);
	Menu findByCode(int code);
	List<Menu> findbyUserCode(int userCode);
	void deleteMenu(int userCode);
//	List<Item> findByCategoryCode(Integer categoryCode);
//	List<Item> findRank();
//	Item findByCode(int code);
//	Item findById(int code);



}
