package com.root.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.root.models.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByMobileNumber(String mobileNumber);

	public User findByEmail(String email);

}
