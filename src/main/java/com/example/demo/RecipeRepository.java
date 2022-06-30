package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository {
	void MenuInsert(int recipeCategory,int userCode,String recipeName, String recipeComment);
	int MenuCodeNextval();
	int MaterialCodeNextval();
	void MaterialInsert(String materialName);
	void MenuDetailInsert(int menuCode,int materialCode, String materialNum);
	void RecipeInsert(int menuCode,int recipeOrder,String recipeDetail);
}
