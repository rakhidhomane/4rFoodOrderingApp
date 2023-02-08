package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dao.UserDao;
import com.cybage.exception.CustomException;
import com.cybage.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(int userId) {
		userDao.deleteById(userId);
	}

	@Override
	public User findByUserEmailAndUserPassword(String email, String password) throws CustomException {
		User user = findByUserEmail(email);
		if(user==null){
			throw new CustomException("user not found");
		}
		if ((user.getUserPassword().equals(password)) && user.getAttemptsCount() <= 3) {
			user.setAttemptsCount(0);
			userDao.save(user);
			return userDao.findByUserEmailAndUserPassword(email, password);
		} else {
			user.setAttemptsCount(user.getAttemptsCount() + 1);
			userDao.save(user);
			return null;
		}
	}

	@Override
	public User findByUserId(int userId) {
		return userDao.findById(userId).orElse(null);

	}

	@Override
	public User saveUser(User user, int userId) {
		User updatedUser = userDao.findById(userId).get();
		updatedUser.setAddress(user.getAddress());
		return userDao.save(updatedUser);
	}

	// @Override
	// public User saveUser(User user, int userId) {
	// user.setUserId(userId);
	// return userDao.save(user);
	// }

	@Override
	public User updateUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User findByUserEmail(String email) {
		if (userDao.findByUserEmail(email) == null) {
			return null;
		} else {
			return userDao.findByUserEmail(email);
		}
	}

	@Override
	public List<User> userList() {
		return userDao.findAll();
	}

	@Override
	public int usercount() {
		return (int) userDao.count();
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User unblock(User user) {
		user.setAttemptsCount(0);
		return userDao.save(user);
	}
}
