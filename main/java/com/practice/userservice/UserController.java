package com.practice.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users == null || users.isEmpty()) {            
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
		 return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping (value="/addUsers")
	public ResponseEntity<Object> createUsers(@RequestBody List<User> users){
		users.stream().forEach(user -> userService.saveUser(user));
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping (value="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = userService.saveUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@GetMapping(value="get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userService.getUser(id);
		if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
		 return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping (value="/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		user.setId(id);
		User updatedUser = userService.update(user);
		if (updatedUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
		 return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping (value = "/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping (value = "/deleteAll")
	public ResponseEntity<User> deleteAll(){
		userService.deleteAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}


