package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Material {
	@Id
	@Column(name="menu_detail_code")
	private Integer menuDetailCode;

	@Column(name="num")
	private String num;

	@Column(name="name")
	private String name;
	
	public Material() {
		
	}

	public Material(Integer menuDetailCode, String num, String name) {
		this.menuDetailCode = menuDetailCode;
		this.num = num;
		this.name = name;
	}

	public Integer getMenuDetailCode() {
		return menuDetailCode;
	}

	public void setMenuDetailCode(Integer menuDetailCode) {
		this.menuDetailCode = menuDetailCode;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
}