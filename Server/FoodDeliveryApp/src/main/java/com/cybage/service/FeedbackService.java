package com.cybage.service;

import java.util.List;

import com.cybage.model.Feedback;
import com.cybage.model.User;

public interface FeedbackService {
	List<Feedback> findFeedbackByUser(User user);
	Feedback addComment(Feedback feedback);
	void deleteFeedback (int feedbackId);
	List<Feedback> findAll();
}
