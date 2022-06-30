package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository{
	User LoginCheck(String email,String password);
	//void UserEdit(Customer customer);
	void Insert(String name, String email, String password);
	void deleteUser(int userCode);
}
