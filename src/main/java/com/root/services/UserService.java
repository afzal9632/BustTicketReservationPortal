package com.root.services;

import java.util.List;

import com.root.exceptions.InvalidRoleException;
import com.root.exceptions.UserException;
import com.root.models.User;

public interface UserService {

	
	public User createUser(User user)throws UserException, InvalidRoleException;
	
	public User updateUser(User user)throws UserException;
	
	public User deleteUser(Integer userId) throws UserException;
	
	public User viewUserById(Integer userId) throws UserException;
	
	public List<User> viewUsers() throws UserException;

	public User currentUser();
	
}
