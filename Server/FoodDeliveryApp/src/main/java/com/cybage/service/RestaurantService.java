package com.cybage.service;

import java.util.List;

import com.cybage.model.Address;
import com.cybage.model.Restaurant;

import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {
	public Restaurant save(Restaurant restaurant, MultipartFile thumbnail);

	public String deleteByRestaurantId(int restaurantId);

	public Restaurant update(Restaurant restaurant, MultipartFile thumbnail);

	public List<Restaurant> findAllRestaurants();

	public List<Restaurant> findRestaurantByArea(Address area);

	public Resource findByThumbnail(String thumbnail);

	public Restaurant findByRestaurantEmailAndRestaurantPassword(String email, String password);

	public Restaurant findByRestaurantId(int restaurantId);

	@Query("select r.rest_id , r.restaurant_name, r.restaurant_email, r.restaurant_user_name, r.restaurant_password from Restaurant r")
	List<Restaurant> restList();

	List<Restaurant> getAllRestaurant();

	int restcount();
	
	Restaurant findByRestaurantEmail(String email);

}
