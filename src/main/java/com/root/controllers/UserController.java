package com.root.controllers;

import java.util.List;


import com.root.exceptions.InvalidRoleException;
import com.root.repository.UserDao;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.root.exceptions.UserException;
import com.root.models.User;
import com.root.services.UserService;

@RestController
@Slf4j
public class UserController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private UserService userService;

	
	
	@PostMapping("/users/register")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws UserException, InvalidRoleException {

		log.info("Inside User registration");
		
		User savedUser= userService.createUser(user);
		
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/users/update")
	public  ResponseEntity<User> updateUser(@Valid @RequestBody User user ) throws UserException {

		log.info("Inside User update");

		User updatedUser= userService.updateUser(user);
				
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/users/admin/{userId}")
	public  ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId  ) throws UserException {

		log.info("Inside Delete User");

		User deletedUser= userService.deleteUser(userId);
				
		return new ResponseEntity<>(deletedUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/users/admin/{userId}")
	public  ResponseEntity<User> viewUser(@PathVariable("userId") Integer userId  ) throws UserException {
		
		User user= userService.viewUserById(userId);
				
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
	@GetMapping("/users/admin")
	public  ResponseEntity<List<User>> viewAllUser( ) throws UserException {
		
		List<User> userList= userService.viewUsers();
				
		return new ResponseEntity<>(userList,HttpStatus.OK);
		
	}

	@GetMapping("/current-user")
	public  ResponseEntity<User> viewCurrentUser(){

		User currentUser = userService.currentUser();

		return new ResponseEntity<>(currentUser,HttpStatus.OK);

	}


}
