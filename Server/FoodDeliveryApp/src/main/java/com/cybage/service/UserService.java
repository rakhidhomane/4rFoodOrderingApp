package com.cybage.service;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.cybage.exception.CustomException;
import com.cybage.model.User;

public interface UserService {
	public User saveUser(User user);
	public void deleteUser(int userId);
	public User findByUserEmailAndUserPassword(String email,String password) throws CustomException;
	public User findByUserId(int userId);
	public User saveUser(User user, int userId);
	public User updateUser(User user);
	public User findByUserEmail(String email);
	
	@Query("select u.user_id ,u.user_name, u.user_email, u.user_mobile_no, u.user_password from User u ")
	List<User> userList();
	
	int usercount();
	
	public List<User> findAllUsers();
	public User unblock(User user);
}
