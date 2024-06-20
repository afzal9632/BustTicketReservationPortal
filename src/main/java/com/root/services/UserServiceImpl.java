package com.root.services;



import java.util.List;
import java.util.Optional;

import com.root.exceptions.InvalidRoleException;
import com.root.models.Authority;
import com.root.repository.AuthorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.root.exceptions.UserException;
import com.root.models.User;
import com.root.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {


		String hashPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashPwd);


		List<Authority> authorities =  user.getAuthorities();

		for(Authority authority:authorities)
		{
			if(authority.getRole().equalsIgnoreCase("user"))
				authority.setRole("ROLE_USER");
			else if (authority.getRole().equalsIgnoreCase("admin"))
				authority.setRole("ROLE_ADMIN");
			else {
				throw new InvalidRoleException("Role can be either user or admin");
			}

			authority.setUser(user);
		}

		return userDao.save(user);
	
	}


	@Override
	public User updateUser(User user) throws UserException {


		Optional<User> existindUser = userDao.findById(user.getUserId());

		if(existindUser.isPresent())
		{
			String hashPwd = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashPwd);

			List<Authority> oldAuthorities = authorityDao.authoritiesByUserId(user.getUserId());

			for(Authority authority:oldAuthorities)
			{
				authority.setUser(null);
				authorityDao.save(authority);
			}


			List<Authority> authorities =  user.getAuthorities();

			for(Authority authority:authorities)
			{
				if(authority.getRole().equalsIgnoreCase("user"))
					authority.setRole("ROLE_USER");
				else if (authority.getRole().equalsIgnoreCase("admin"))
					authority.setRole("ROLE_ADMIN");
				else {
					throw new InvalidRoleException("Role can be either user or admin");
				}

				authority.setUser(user);
			}

			User updatedUser = userDao.save(user);

			List<Authority> unrefrencedAuthorities = authorityDao.unrefrencedAuthorities();

			authorityDao.deleteAll(unrefrencedAuthorities);

			return updatedUser;

		}
		else
			throw new UserException("Invalid User! No user found by given id");
	}
	
	
	@Override
	public User deleteUser(Integer userId) throws UserException {

		User user = userDao.findById(userId).orElseThrow(()-> new UserException("Invalid user Id!"));
		userDao.delete(user);
		return user;
	}

	@Override
	public User viewUserById(Integer userId) throws UserException {

		User user = userDao.findById(userId).orElseThrow(()-> new UserException("Invalid user Id!"));

		return user;
	}
	
	@Override
	public List<User> viewUsers() throws UserException {

		
		List<User> userList = userDao.findAll();
		if(userList.isEmpty()) throw new UserException("No users found!");
		
		return userList;
	}


	@Override
	public User currentUser(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String userName = authentication.getName();

		User currnetUser = userDao.findByEmail(userName);


		return currnetUser;
	}






}
