package com.root.repository;

import com.root.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityDao extends JpaRepository<Authority,Integer> {

    @Query("select a from Authority a where a.user.userId=?1")
    public List<Authority> authoritiesByUserId(Integer userId);

    @Query("select a from Authority a where a.user.userId is null")
    public List<Authority> unrefrencedAuthorities();
}

