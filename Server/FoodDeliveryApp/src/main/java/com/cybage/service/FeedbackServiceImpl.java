package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dao.FeedbackDao;
import com.cybage.model.Feedback;
import com.cybage.model.User;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public List<Feedback> findFeedbackByUser(User user) {
		return feedbackDao.findByUser(user);
	}

	@Override
	public Feedback addComment(Feedback feedback) {
		return feedbackDao.save(feedback);
	}

	@Override
	public void deleteFeedback(int feedbackId) {
		feedbackDao.deleteById(feedbackId);
	}

	@Override
	public List<Feedback> findAll() {
		return feedbackDao.findAll();
	}
}
