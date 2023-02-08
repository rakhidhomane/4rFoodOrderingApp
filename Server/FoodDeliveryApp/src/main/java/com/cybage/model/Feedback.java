package com.cybage.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue
	private int feedbackId;
	private String feedback;
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Feedback() {
		
	}

	public Feedback(int feedbackId, String feedback, int rating, User user) {
		this.feedbackId = feedbackId;
		this.feedback = feedback;
		this.rating = rating;
		this.user = user;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeddback(String feedback) {
		this.feedback = feedback;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedback=" + feedback + ", rating=" + rating + "]";
	}
}
