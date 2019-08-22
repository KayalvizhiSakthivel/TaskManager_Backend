package com.iiht.capsuleproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.capsuleproject.dao.User_Repo;

import com.iiht.capsuleproject.model.User;

@Service
public class UserService_impl {

	@Autowired
	private User_Repo user_repo;

	public User addUser(User user) {
		User u = user_repo.save(user);
		return u;
	}

	public User updateUser(User user) {
		User u = user_repo.save(user);
		return u;
	}

	public Optional<User> findUserById(long user_id) {
		// TODO Auto-generated method stub

		return user_repo.findById(user_id);

	}

	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) user_repo.findAll();

	}

	public void delete(User user) {
		// TODO Auto-generated method stub
		user_repo.delete(user);
	}

}
