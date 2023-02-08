package com.cybage.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.FoodMenusRequestDTO;
import com.cybage.dto.FoodMenusResponseDTO;
import com.cybage.dto.RestaurantRequestDTO;
import com.cybage.dto.RestaurantResponseDTO;
import com.cybage.exception.CustomException;
import com.cybage.model.FoodMenus;
import com.cybage.model.OrderInfo;
import com.cybage.model.Restaurant;
import com.cybage.service.AddressService;
import com.cybage.service.EmailSenderService;
import com.cybage.service.FoodMenusService;
import com.cybage.service.OrderInfoService;
import com.cybage.service.RestaurantService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private OrderInfoService orderService;
	
	@Autowired
	private FoodMenusService foodMenusService;
	// static Logger logger1=LogManager.getLogger(RestaurantController.class);

	@Autowired
	private EmailSenderService senderService;

	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurant(RestaurantRequestDTO restaurantRequestDTO) throws CustomException {
		Restaurant newRestaurant = null;
		try {
			newRestaurant = restaurantService.save(RestaurantRequestDTO.toEntity(restaurantRequestDTO),
					restaurantRequestDTO.getThumbnail());
			senderService.sendEmail(newRestaurant.getRestaurantEmail(), newRestaurant.getRestaurantPassword());
		} catch (Exception e) {
			throw new CustomException("Restaurant Not Added");
		}
		return new ResponseEntity<Restaurant>(newRestaurant, HttpStatus.OK);
	}

	@PutMapping("/updateRestaurant/{restaurantId}")
	public ResponseEntity<?> updateRestaurant(@PathVariable int restaurantId,
			RestaurantRequestDTO restaurantRequestDTO) {
		Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
		restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
		restaurant.setRestaurantUserName(restaurantRequestDTO.getRestaurantUserName());
		return new ResponseEntity<>(
				RestaurantResponseDTO
						.fromEntity(restaurantService.update(restaurant, restaurantRequestDTO.getThumbnail())),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteRestaurant/{restaurantId}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable int restaurantId) throws CustomException {
		String response = "";
		List<FoodMenus> restaurantFood = foodMenusService.getByRestaurantId(restaurantId);
		
		
		
		response = restaurantService.deleteByRestaurantId(restaurantId);
		return new ResponseEntity<>(restaurantFood, HttpStatus.OK);
	}

	@GetMapping("/allRestaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurant() {
		return new ResponseEntity<List<Restaurant>>(restaurantService.findAllRestaurants(), HttpStatus.OK);
	}

	@GetMapping("/{thumbnail}")
	public Resource findThumbnail(@PathVariable String thumbnail) {
		return restaurantService.findByThumbnail(thumbnail);
	}

	@GetMapping("/getRestaurantById/{restaurantId}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int restaurantId) {
		return new ResponseEntity<Restaurant>(restaurantService.findByRestaurantId(restaurantId), HttpStatus.OK);
	}


	@PostMapping("/login")
	public ResponseEntity<?> restaurantLogin(@RequestBody Restaurant restaurant) throws CustomException {
		Restaurant loginRestaurant = restaurantService.findByRestaurantEmailAndRestaurantPassword(
				restaurant.getRestaurantEmail(), restaurant.getRestaurantPassword());
		if (loginRestaurant != null) {
			// logger1.info("Restaurant "+loginRestaurant.getRestaurantUserName()+" logged
			// in");
			return new ResponseEntity<>(loginRestaurant, HttpStatus.OK);
		}
		throw new CustomException("Email or password is wrong");
	}
}
