package com.cybage.controller;

import java.util.List;

import com.cybage.dto.FoodMenusResponseDTO;
import com.cybage.dto.FoodMenusRequestDTO;
import com.cybage.model.FoodMenus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.service.FoodMenusService;
import com.cybage.service.RestaurantService;

@RestController
@RequestMapping("/food-menu")
@CrossOrigin("http://localhost:3000")
public class FoodMenuController {
	@Autowired
	private FoodMenusService foodMenusService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/{foodId}")
	public ResponseEntity<?> addFoodMenus(@PathVariable int foodId ,FoodMenus foodMenus) {
		return new ResponseEntity<>(foodMenusService.findById(foodId), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addFoodMenus")
	public ResponseEntity<?> addFoodMenus(FoodMenusRequestDTO foodMenusRequestDTO) {
		FoodMenusResponseDTO newFoodMenu=null;
		try {
		newFoodMenu = FoodMenusResponseDTO.fromEntity(foodMenusService.save(FoodMenusRequestDTO.toEntity(foodMenusRequestDTO),foodMenusRequestDTO.getThumbnail() ,restaurantService.findByRestaurantId(foodMenusRequestDTO.getRestId())));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Food Item Not Added!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newFoodMenu, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateFoodMenu/{foodId}")
	public ResponseEntity<?> updateFoodMenu(@PathVariable int foodId, FoodMenusRequestDTO foodMenusRequestDTO) {
		FoodMenus foodItem = foodMenusService.findByFoodId(foodId);
		foodItem.setFoodName(foodMenusRequestDTO.getFoodName());
		foodItem.setFoodCategory(foodMenusRequestDTO.getFoodCategory());
		return new ResponseEntity<>(FoodMenusResponseDTO.fromEntity(foodMenusService.updateFoodMenu(foodItem,foodMenusRequestDTO.getThumbnail())), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{foodId}")
	public ResponseEntity<String> deleteFoodMenuById(@PathVariable int foodId) {
		try {
			foodMenusService.deleteFoodMenu(foodId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Enter Valid Food Menu", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Food Menu deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/allFoodMenus")
	public ResponseEntity<List<FoodMenus>> getAllFoodMenus() {
		return new ResponseEntity<List<FoodMenus>>(foodMenusService.findAllFoodMenus(), HttpStatus.OK);
	}

	@GetMapping("/getFoodByRestaurant/{restuarant_id}")
	public ResponseEntity<?> getAllFoodByRestaurant(@PathVariable int restuarant_id) {
		List<FoodMenus> foodItems = foodMenusService.getByRestaurantId(restuarant_id);
		return new ResponseEntity<>(foodItems, HttpStatus.OK);
	}
	
	@PutMapping("/removeOffer/{foodId}")
	public ResponseEntity<?> removeFoodOffer(@PathVariable int foodId, FoodMenusRequestDTO foodMenusRequestDTO) {
		FoodMenus foodItem = foodMenusService.findByFoodId(foodId);
		foodItem.setOffer(0);
		return new ResponseEntity<>(FoodMenusResponseDTO.fromEntity(foodMenusService.updateOffer(foodItem)), HttpStatus.OK);
	}

	@PutMapping("/updateOffer/{foodId}")
	public ResponseEntity<?> updateFoodOffer(@PathVariable int foodId, FoodMenusRequestDTO foodMenusRequestDTO) {
		FoodMenus foodItem = foodMenusService.findByFoodId(foodId);
		foodItem.setOffer(foodMenusRequestDTO.getOffer());
		return new ResponseEntity<>(FoodMenusResponseDTO.fromEntity(foodMenusService.updateOffer(foodItem)), HttpStatus.OK);
	}
	
	@GetMapping("/allOffers")
	public ResponseEntity<List<FoodMenus>> getAllFoodOffer() {
		return new ResponseEntity<List<FoodMenus>>(foodMenusService.findByOfferNot(0.0d), HttpStatus.OK);
	}
	
	@PostMapping("/addOffer")
	public ResponseEntity<?> addFoodOffer(FoodMenusRequestDTO foodMenusRequestDTO) {
		FoodMenus foodItem = foodMenusService.findByFoodId(foodMenusRequestDTO.getFoodId());
		foodItem.setOffer(foodMenusRequestDTO.getOffer());
		return new ResponseEntity<>(FoodMenusResponseDTO.fromEntity(foodMenusService.updateOffer(foodItem)), HttpStatus.OK);
	}
	
	@GetMapping("/getFood")
	public ResponseEntity<List<FoodMenus>> getAllFoodWithoutOffer() {
		return new ResponseEntity<List<FoodMenus>>(foodMenusService.findByOffer(0.0d), HttpStatus.OK);
	}
}
