package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TitleMenu {
	@Id
	@Column(name="menu_code")
	private Integer menuCode;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="image")
	private String image;
	
	public TitleMenu() {
		
	}

	public TitleMenu(Integer menuCode, String userName, String menuName, String comment, String image) {
		this.menuCode = menuCode;
		this.userName = userName;
		this.menuName = menuName;
		this.comment = comment;
		this.image = image;
	}

	public Integer getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(Integer menuCode) {
		this.menuCode = menuCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
		
}