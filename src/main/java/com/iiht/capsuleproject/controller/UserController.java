package com.iiht.capsuleproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.iiht.capsuleproject.model.User;
import com.iiht.capsuleproject.service.UserService_impl;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserService_impl userimpl;

	/* add new user */
	@PostMapping("/addUser")

	public User addUser(@RequestBody User user) {
		return userimpl.addUser(user);
	}

	/* View user */
	@GetMapping("/viewUser/{id}")
	public Optional<User> findUserById(@PathVariable long id) {
		return userimpl.findUserById(id);
	}

	/* View all users */
	@GetMapping("/viewUsers")
	public Iterable<User> findAllUser() {
		return userimpl.findAllUser();
	}

	/* Update user */
	@PutMapping("/updateUser")
	public void updateUser(@RequestBody User user) {
		userimpl.updateUser(user);
	}

	/* DELETE user */
	@PostMapping("/deleteUser")
	public void deleteUser(@RequestBody User user) {
		userimpl.delete(user);

	}

}
