package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public interface TitleMenuRepository {
	TitleMenu findByMenuCode(int menuCode);

}
