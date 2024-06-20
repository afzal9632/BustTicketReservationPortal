package com.root.controllers;

import com.root.models.Authority;
import com.root.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @GetMapping("/admin/getAuthorities/{userId}")
    public ResponseEntity<List<Authority>> getAuthorityByUserId(@PathVariable("userId") Integer userId)
    {
        List<Authority> authorities = authorityService.getAuthoritiesByUserId(userId);

        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }
}
