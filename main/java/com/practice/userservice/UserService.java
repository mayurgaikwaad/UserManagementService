package com.practice.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public User saveUser(User user) {
		User savedUser = userRepo.save(user);
		return savedUser;
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User getUser(Long id) {
		return userRepo.findById(id).get();
	}

	public User update(User user) {
		return userRepo.save(user);
		
	}

	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}
	
}
