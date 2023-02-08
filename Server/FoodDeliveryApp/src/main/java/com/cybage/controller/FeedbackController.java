package com.cybage.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.model.Feedback;
import com.cybage.model.User;
import com.cybage.service.FeedbackService;
import com.cybage.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/feedback")
public class FeedbackController {
	
	static Logger logger=LogManager.getLogger(FeedbackController.class);
	
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> findFeedbackByUserId(@PathVariable int userId) {
		User user = userService.findByUserId(userId);
		return new ResponseEntity<>(feedbackService.findFeedbackByUser(user), HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<?> getAllFeedback() {
		return new ResponseEntity<>(feedbackService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{feedbackId}")
	public ResponseEntity<?> deleteByFeedbackId(@PathVariable int feedbackId) {
		feedbackService.deleteFeedback(feedbackId);
		return new ResponseEntity<>("feedback Deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/addFeedback")
	public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback) {
		logger.info("Customer "+userService.findByUserId(feedback.getUser().getUserId()).getUserName()+" Gives Feedback.");
		return new ResponseEntity<>( feedbackService.addComment(feedback), HttpStatus.OK);
	}
}
