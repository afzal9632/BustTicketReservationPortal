package com.root.services;

import com.root.models.Authority;

import java.util.List;

public interface AuthorityService {

    public List<Authority> getAuthoritiesByUserId(Integer userId);
}
