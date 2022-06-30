package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	EntityManager em;

	public User LoginCheck(String email,String password) {
		String sql = "SELECT * FROM users WHERE email = :email AND password = :password";
		Query query = em.createNativeQuery(sql, User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		}
		return user;
	}

	public void Insert(String name,String email, String password) {
		String sql = "INSERT INTO users(name,email,password) VALUES(:name ,:email,:password )";
		Query query = em.createNativeQuery(sql, User.class);
		query.setParameter("name", name);
		query.setParameter("email", email);
		query.setParameter("password", password);
		query.executeUpdate();
	}

	public void deleteUser(int userCode) {
		String sql = "DELETE FROM users WHERE user_code = :user_code";
		Query query = em.createNativeQuery(sql);
		query.setParameter("user_code",userCode);
		query.executeUpdate();
		String sql = "DELETE FROM menu WHERE user_code = :user_code";
		Query query = em.createNativeQuery(sql);
		query.setParameter("user_code",userCode);
		query.executeUpdate();
	}

//	public void UserEdit(Customer customer) {
//		String sql ="UPDATE customer SET name = :name, address = :address, tel = :tel, email = :email";
//		Query query = em.createNativeQuery(sql, Customer.class);
//		query.setParameter("name", customer.getName());
//		query.setParameter("address", customer.getAddress());
//		query.setParameter("tel", customer.getTel());
//		query.setParameter("email", customer.getEmail());
//		query.executeUpdate();
//	}


}
