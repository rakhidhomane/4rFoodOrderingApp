package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cybage.dao.FoodMenusDao;
import com.cybage.model.FoodMenus;
import com.cybage.model.Restaurant;
import com.cybage.util.StorageService;

@Service
@Transactional
public class FoodMenusServiceImpl implements FoodMenusService {
	@Autowired
	private FoodMenusDao foodMenusDao;

	@Autowired
	private StorageService storageService;

	@Override
	public FoodMenus save(FoodMenus foodMenus, MultipartFile thumbnail, Restaurant restaurant) {
		String fileName = storageService.store(thumbnail);
		foodMenus.setThumbnail(fileName);
		foodMenus.setRestaurant(restaurant);
		return foodMenusDao.save(foodMenus);
	}

	@Override
	public void deleteFoodMenu(int id) {
		foodMenusDao.deleteById(id);
	}

	@Override
	public FoodMenus findByFoodId(int foodId) {
		return foodMenusDao.findById(foodId).get();
	}

	@Override
	public FoodMenus updateFoodMenu(FoodMenus foodMenus, int foodId) {
		return foodMenusDao.save(foodMenus);
	}

	@Override
	public List<FoodMenus> findAllFoodMenus() {
		return foodMenusDao.findAll();
	}

	@Override
	public FoodMenus findById(int foodId) {
		return foodMenusDao.findById(foodId).get();
	}

	@Override
	public List<FoodMenus> getByRestaurantId(int restuarantId) {
		return foodMenusDao.findFoodByRestaurantId(restuarantId);
	}

	@Override
	public FoodMenus updateFoodMenu(FoodMenus foodMenus, MultipartFile thumbnail) {
		String fileName = storageService.store(thumbnail);
		foodMenus.setThumbnail(fileName);
		return foodMenusDao.save(foodMenus);
	}

	@Override
	public FoodMenus updateOffer(FoodMenus foodItem) {
		return foodMenusDao.save(foodItem);
	}

	@Override
	public List<FoodMenus> findByOfferNot(double offer) {
		return foodMenusDao.findByOfferNot(offer);
	}

	@Override
	public List<FoodMenus> findByOffer(double offer) {
		return foodMenusDao.findByOffer(offer);
	}

}
