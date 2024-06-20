package com.root.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.root.models.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer>{
//    @Query("select a from Authority a where a.user.userId=?1")

    @Query("delete from Feedback where feedbackId=?1")
    public void deleteByFeedbackId(Integer id);

}
