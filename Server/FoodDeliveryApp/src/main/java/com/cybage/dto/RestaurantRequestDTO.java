package com.cybage.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cybage.model.Address;
import com.cybage.model.Restaurant;

public class RestaurantRequestDTO {
	private int restId;
	private String restaurantName;
	private String restaurantUserName;
	private String restaurantPassword;
	private String restaurantEmail;
	private MultipartFile thumbnail;
	private int attemptCount;
	private String area;
	private String street;
	private String pincode;
	public RestaurantRequestDTO() {
		
	}

	public RestaurantRequestDTO(int restId, String restaurantName, String restaurantUserName, String restaurantPassword,
			String restaurantEmail, MultipartFile thumbnail, int attemptCount, String area, String street,
			String pincode) {
		this.restId = restId;
		this.restaurantName = restaurantName;
		this.restaurantUserName = restaurantUserName;
		this.restaurantPassword = restaurantPassword;
		this.restaurantEmail = restaurantEmail;
		this.thumbnail = thumbnail;
		this.attemptCount = attemptCount;
		this.area = area;
		this.street = street;
		this.pincode = pincode;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantUserName() {
		return restaurantUserName;
	}

	public void setRestaurantUserName(String restaurantUserName) {
		this.restaurantUserName = restaurantUserName;
	}

	public String getRestaurantPassword() {
		return restaurantPassword;
	}

	public void setRestaurantPassword(String restaurantPassword) {
		this.restaurantPassword = restaurantPassword;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getAttemptCount() {
		return attemptCount;
	}

	public void setAttemptCount(int attemptCount) {
		this.attemptCount = attemptCount;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	

	public static Restaurant toEntity(RestaurantRequestDTO dto) {
		Restaurant entity=new Restaurant();
		entity.setAddress(new Address( dto.getArea(), dto.getStreet(), dto.getPincode()));
		BeanUtils.copyProperties(dto, entity,"thumbnail");
		return entity;
	}


}
