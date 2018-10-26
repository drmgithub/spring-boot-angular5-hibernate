package com.devglan.userportal;

import java.util.List;

public interface UserDao {

	ResponseStatus create(User user);

	User login(User user);

	User delete(int id);

	List<User> findAll();

	User findById(int id);

	User update(User user);
	
	User forgetPassword(String email);
}
