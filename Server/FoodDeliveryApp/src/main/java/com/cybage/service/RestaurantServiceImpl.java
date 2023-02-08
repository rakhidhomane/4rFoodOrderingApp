package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cybage.dao.RestaurantDao;
import com.cybage.model.Address;
import com.cybage.model.Restaurant;
import com.cybage.util.StorageService;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private StorageService storageService;

	@Override
	public Restaurant save(Restaurant restaurant, MultipartFile thumbnail) {
		String fileName=storageService.store(thumbnail);
		restaurant.setThumbnail(fileName);
		return restaurantDao.save(restaurant);
	}

	@Override
	public String deleteByRestaurantId(int restaurantId) {
		restaurantDao.deleteById(restaurantId);
		return "Restaurant deleted successfully";
	}

	@Override
	public Restaurant update(Restaurant restaurant,MultipartFile thumbnail) {
		String fileName=storageService.store(thumbnail);
		restaurant.setThumbnail(fileName);
		return restaurantDao.save(restaurant);
	}

	@Override
	public List<Restaurant> findAllRestaurants() {
		return restaurantDao.findAll();
	}

	@Override
	public List<Restaurant> findRestaurantByArea(Address address) {
		return restaurantDao.findByAddress(address);
	}

	@Override
	public Resource findByThumbnail(String thumbnail){
		return storageService.load(thumbnail);
	}
	@Override
	public Restaurant findByRestaurantEmailAndRestaurantPassword(String email, String password) {
		return restaurantDao.findByRestaurantEmailAndRestaurantPassword(email, password);
	}

	@Override
	public Restaurant findByRestaurantId(int restaurantId) {
		return restaurantDao.findById(restaurantId).get();
	}
	
	@Override
	public List<Restaurant> restList() {
		return restaurantDao.findAll();
	}
	@Override
	public List<Restaurant> getAllRestaurant() {		
		return restaurantDao.findAll();
	}
	@Override
	public int restcount() {
		return (int) restaurantDao.count();
	}

	@Override
	public Restaurant findByRestaurantEmail(String email) {
		return restaurantDao.findByRestaurantEmail(email);
	}

}
