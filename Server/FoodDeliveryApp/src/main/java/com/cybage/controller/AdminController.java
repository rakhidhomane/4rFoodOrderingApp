package com.cybage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.exception.CustomException;
import com.cybage.model.User;
import com.cybage.service.ExportService;
import com.cybage.service.OrderInfoService;
import com.cybage.service.RestaurantService;
import com.cybage.service.UserService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private RestaurantService restService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private ExportService exportService;

	@GetMapping("/userCount")
	public ResponseEntity<?> finduserCount() {
		return new ResponseEntity<>(userService.usercount(), HttpStatus.OK);
	}

	@GetMapping("/orderCount")
	public ResponseEntity<?> findorderCount() {
		return new ResponseEntity<>(orderInfoService.ordercount(), HttpStatus.OK);
	}

	@GetMapping("/restCount")
	public ResponseEntity<?> findrestCount() {
		return new ResponseEntity<>(restService.restcount(), HttpStatus.OK);
	}
	
	@PutMapping("/unblock/{userId}")
	public ResponseEntity<?> unblockUser(@PathVariable int userId, @RequestBody User user) throws CustomException{
		User updatedUser = userService.unblock(user);
		if(updatedUser!=null) {
		return new ResponseEntity<>(user, HttpStatus.OK);
		}
		throw new CustomException("user not found");
	}

	@GetMapping("/export-user-details")
	public ResponseEntity<?> exportUserDetails(){
		exportService.printUser();
		return new ResponseEntity<>("User details has been exported to excel file", HttpStatus.OK);
	}

	@GetMapping("/export-foodItem-details")
	public ResponseEntity<?> exportFoodItemDetails(){
		exportService.printFoodItemData();;
		return new ResponseEntity<>("Food Item Details has been exported to excel file", HttpStatus.OK);
	}
	
	@GetMapping("/export-restaurant-details")
	public ResponseEntity<?> exportRestaurantDetails(){
		exportService.printRestaurantData();
		return new ResponseEntity<>("Restaurant Details has been exported to excel file", HttpStatus.OK);
	}
}
