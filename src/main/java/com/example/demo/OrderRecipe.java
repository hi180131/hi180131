package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderRecipe {
	@Id
	@Column(name="recipe_code")
	private Integer recipeCode;
	
	@Column(name="recipe_order")
	private Integer recipeOrder;

	@Column(name="recipe_detail")
	private String recipeDetail;
	
	public OrderRecipe() {
		
	}

	public OrderRecipe(Integer recipeCode, Integer recipeOrder, String recipeDetail) {
		this.recipeCode = recipeCode;
		this.recipeOrder = recipeOrder;
		this.recipeDetail = recipeDetail;
	}

	public Integer getRecipeCode() {
		return recipeCode;
	}

	public void setRecipeCode(Integer recipeCode) {
		this.recipeCode = recipeCode;
	}

	public Integer getRecipeOrder() {
		return recipeOrder;
	}

	public void setRecipeOrder(Integer recipeOrder) {
		this.recipeOrder = recipeOrder;
	}

	public String getRecipeDetail() {
		return recipeDetail;
	}

	public void setRecipeDetail(String recipeDetail) {
		this.recipeDetail = recipeDetail;
	}	
		
}