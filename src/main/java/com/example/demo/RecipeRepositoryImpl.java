package com.example.demo;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class RecipeRepositoryImpl implements RecipeRepository {

	@Autowired
	EntityManager em;

	public void MenuInsert(int recipeCategory, int userCode, String recipeName, String recipeComment) {
		String sql = "INSERT INTO menu(category_code,user_code,name,comment) "
				+ "VALUES(:recipeCategory,:userCode,:recipeName ,:recipeComment)";
		Query query = em.createNativeQuery(sql, Menu.class);
		query.setParameter("recipeCategory", recipeCategory);
		query.setParameter("userCode", userCode);
		query.setParameter("recipeName", recipeName);
		query.setParameter("recipeComment", recipeComment);
		query.executeUpdate();
	}

	public int MenuCodeNextval() {
		String sql = "select nextval('menu_menu_code_seq');";
		Query query = em.createNativeQuery(sql);
        int nextVal = Integer.parseInt("" + (BigInteger) query.getSingleResult());
		return nextVal;
	}

	public int MaterialCodeNextval() {
		String sql = "select nextval('material_material_code_seq');";
		Query query = em.createNativeQuery(sql);
		int nextVal = Integer.parseInt("" + (BigInteger) query.getSingleResult());
		return nextVal;
	}

	public void MaterialInsert(String materialName) {
		String sql = "INSERT INTO material(name) VALUES(:materialName)";
		Query query = em.createNativeQuery(sql, Material.class);
		query.setParameter("materialName", materialName);
		query.executeUpdate();
	}

	public void MenuDetailInsert(int menuCode,int materialCode, String materialNum) {
		String sql = "INSERT INTO menu_detail(menu_code,material_code,num) VALUES(:menuCode,:materialCode,:materialNum)";
		Query query = em.createNativeQuery(sql, Menu.class);
		query.setParameter("menuCode", menuCode);
		query.setParameter("materialCode", materialCode);
		query.setParameter("materialNum", materialNum);
		query.executeUpdate();
	}

	void RecipeInsert(int menuCode,int recipeOrder,String recipeDetail) {
		String sql = "INSERT INTO recipe(menu_code,recipe_order,recipe_detail) VALUES(:menuCode,:recipeOrder,:recipeDetail)";
		Query query = em.createNativeQuery(sql, Menu.class);
		query.setParameter("menuCode", menuCode);
		query.setParameter("recipeOrder", recipeOrder);
		query.setParameter("recipeDetail", recipeDetail);
		query.executeUpdate();
	}
}
