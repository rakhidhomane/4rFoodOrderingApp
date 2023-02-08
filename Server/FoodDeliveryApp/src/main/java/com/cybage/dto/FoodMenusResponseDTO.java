package com.cybage.dto;

import org.springframework.beans.BeanUtils;

import com.cybage.model.FoodMenus;

public class FoodMenusResponseDTO {
	private int foodId;
	private String foodName;
	private double price;
	private double offer;
	private String foodCategory;
	private String thumbnail;
	private int restId;
	private String restaurantName;
	private String restaurantUserName;
	private String restaurantPassword;
	private String restaurantEmail;
	private int attemptCount;

	public FoodMenusResponseDTO() {
		
	}

	public FoodMenusResponseDTO(int foodId, String foodName, double price, double offer, String foodCategory,
			String thumbnail, int restId, String restaurantName, String restaurantUserName,
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
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
	public static FoodMenusResponseDTO fromEntity(FoodMenus entity) {
		FoodMenusResponseDTO foodMenusResponseDTO = new FoodMenusResponseDTO();
		BeanUtils.copyProperties(entity,foodMenusResponseDTO);
		foodMenusResponseDTO.setRestId(entity.getRestaurant().getRestId());
		foodMenusResponseDTO.setRestaurantUserName(entity.getRestaurant().getRestaurantUserName());
		foodMenusResponseDTO.setRestaurantEmail(entity.getRestaurant().getRestaurantEmail());
		foodMenusResponseDTO.setRestaurantName(entity.getRestaurant().getRestaurantName());
		foodMenusResponseDTO.setRestaurantPassword(entity.getRestaurant().getRestaurantPassword());
		foodMenusResponseDTO.setAttemptCount(entity.getRestaurant().getAttemptCount());
		return foodMenusResponseDTO;
	}

	@Override
	public String toString() {
		return "FoodMenusResponseDTO [foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + ", offer="
				+ offer + ", foodCategory=" + foodCategory + ", thumbnail=" + thumbnail + ", restId=" + restId
				+ ", restaurantName=" + restaurantName + ", restaurantUserName=" + restaurantUserName
				+ ", restaurantPassword=" + restaurantPassword + ", restaurantEmail=" + restaurantEmail
				+ ", attemptCount=" + attemptCount + "]";
	}
}
