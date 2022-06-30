package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository {
	List<Material> findByMenuCode(int menuCode);
}
