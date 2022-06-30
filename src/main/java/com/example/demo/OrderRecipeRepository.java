package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecipeRepository {
	List<OrderRecipe> findByMenuCode(int menuCode);
}
