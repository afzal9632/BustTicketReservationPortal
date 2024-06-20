package com.root.services;

import com.root.models.Authority;
import com.root.repository.AuthorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    AuthorityDao authorityDao;

    @Override
    public List<Authority> getAuthoritiesByUserId(Integer userId) {


        List<Authority> authorities = authorityDao.authoritiesByUserId(userId);

        return authorities;
    }
}
