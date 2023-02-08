package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Feedback;
import com.cybage.model.User;

public interface FeedbackDao extends JpaRepository<Feedback, Integer>{

	List<Feedback> findByUser(User user);

}
