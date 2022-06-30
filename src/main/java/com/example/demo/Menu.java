package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	@Id
	@Column(name = "menu_code")
	private Integer menuCode;

	@Column(name = "category_code")
	private Integer categoryCode;

	@Column(name = "user_code")
	private Integer userCode;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@Column(name = "comment")
	private String comment;
	
	public Menu() {
		
	}

	public Menu(Integer menuCode, Integer categoryCode, Integer userCode, String name, String image, String comment) {
		this.menuCode = menuCode;
		this.categoryCode = categoryCode;
		this.userCode = userCode;
		this.name = name;
		this.image = image;
		this.comment = comment;
	}

	public Integer getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(Integer menuCode) {
		this.menuCode = menuCode;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
