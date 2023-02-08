package com.cybage.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userMobileNo;
	private int attemptsCount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;


	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<UserOrder> userOrders;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Feedback> feedbackList;

	public User() {
		this.feedbackList = new ArrayList<>();
	}

	public User(int userId, String userName, String userPassword, String userEmail, String userMobileNo,
			int attemptsCount, Address address,
			List<UserOrder> userOrders, List<Feedback> feedbackList) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userMobileNo = userMobileNo;
		this.attemptsCount = attemptsCount;
		this.address = address;
		this.userOrders = userOrders;
		this.feedbackList = feedbackList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public int getAttemptsCount() {
		return attemptsCount;
	}

	public void setAttemptsCount(int attemptsCount) {
		this.attemptsCount = attemptsCount;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<UserOrder> getUserOrders() {
		return userOrders;
	}

	public void setUserOrders(List<UserOrder> userOrders) {
		this.userOrders = userOrders;
	}

	

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userMobileNo=" + userMobileNo + ", attemptsCount=" + attemptsCount + "]";
	}

}
