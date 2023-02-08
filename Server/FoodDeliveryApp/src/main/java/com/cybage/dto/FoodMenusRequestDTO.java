package com.cybage.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import com.cybage.model.FoodMenus;

public class FoodMenusRequestDTO {
	private int foodId;
	private String foodName;
	private double price;
	private double offer;
	private String foodCategory;
	private MultipartFile thumbnail;
	private int restId;
	private String restaurantName;
	private String restaurantUserName;
	private String restaurantPassword;
	private String restaurantEmail;
	private int attemptCount;

	public FoodMenusRequestDTO() {

	}

	public FoodMenusRequestDTO(int foodId, String foodName, double price, double offer, String foodCategory,
			MultipartFile thumbnail, int restId, String restaurantName, String restaurantUserName,
			String restaurantPassword, String restaurantEmail, int attemptCount) {
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.offer = offer;
		this.foodCategory = foodCategory;
		this.thumbnail = thumbnail;
		this.restId = restId;
		this.restaurantName = restaurantName;
		this.restaurantUserName = restaurantUserName;
		this.restaurantPassword = restaurantPassword;
		this.restaurantEmail = restaurantEmail;
		this.attemptCount = attemptCount;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
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

	public int getAttemptCount() {
		return attemptCount;
	}

	public void setAttemptCount(int attemptCount) {
		this.attemptCount = attemptCount;
	}

	public static FoodMenus toEntity(FoodMenusRequestDTO foodMenusRequestDTO) {
		FoodMenus entity = new FoodMenus();
		BeanUtils.copyProperties(foodMenusRequestDTO, entity, "thumbnail");
		return entity;
	}

	@Override
	public String toString() {
		return "FoodMenusDTO [foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + ", offer=" + offer
				+ ", foodCategory=" + foodCategory + ", thumbnail=" + thumbnail + ", restId=" + restId
				+ ", restaurantName=" + restaurantName + ", restaurantUserName=" + restaurantUserName
				+ ", restaurantPassword=" + restaurantPassword + ", restaurantEmail=" + restaurantEmail
				+ ", attemptCount=" + attemptCount + "]";
	}

}
