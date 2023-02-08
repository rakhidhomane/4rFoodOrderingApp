package com.cybage.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.cybage.exception.CustomException;
import com.cybage.model.User;
import com.cybage.service.EmailSenderService;
import com.cybage.service.UserService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/food-delivery")
public class UserController {
	static Logger logger=LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSenderService senderService;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable int id){
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted",HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody User user) throws CustomException{
		User loginUser = userService.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
		//System.out.println(loginUser);
		User user1 = userService.findByUserEmail(user.getUserEmail());
		//System.out.println(user1.getAttemptsCount());
		if(loginUser == null) {
			if(user1.getAttemptsCount()<=3) {	
				logger.error("customer" +user1.getUserName()+"Logged in with wrong crediantials");
				return new ResponseEntity<>("Email or password is wrong", HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<>("Maximum Attempts completed. Please contact admin....",HttpStatus.LOCKED);
			}
			
		}else {
			if(user1.getUserName().equals("admin")) {
				return new ResponseEntity<>(user1 , HttpStatus.OK);
			}
			else {
				int otpCheck=senderService.sendOTP(user.getUserEmail());
			logger.info("Customer "+loginUser.getUserName()+" logged in");
			 return new ResponseEntity<>(otpCheck,HttpStatus.OK) ;
		}
		}	
	}
		
	@PutMapping("/addAddress/{userId}")
	public ResponseEntity<?> userLogin(@RequestBody User user,@PathVariable int userId){
		return new ResponseEntity<>(userService.saveUser(user,userId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> userUpdate(@PathVariable int id, @RequestBody User user){
		User updateUser=userService.findByUserId(id);
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getUserById/{id}")

	public ResponseEntity<?> getUserById(@PathVariable int id) throws CustomException{
		User user=userService.findByUserId(id);
		// if(user!=null)
		// 	return new ResponseEntity<>(user,HttpStatus.OK);
		// return new ResponseEntity<>("No user found with this id ",HttpStatus.NOT_ACCEPTABLE);
		if(user==null){
			throw new CustomException("user not found");
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<?> getUserByEmailId(@PathVariable String email){
		User user = userService.findByUserEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> list =userService.findAllUsers();
		User user = userService.findByUserId(1);
		list.remove(user);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/sendOTP/{email}")
	public ResponseEntity<?> getOTPForForgetPassword(@PathVariable String email){
		int otpCheck=0;
		User user = userService.findByUserEmail(email);
		if(user != null) 
			otpCheck=senderService.sendOTP(user.getUserEmail());		
		return new ResponseEntity<>(otpCheck,HttpStatus.OK) ;
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> userLogout(@RequestBody User user) throws CustomException{
		//userService.findByUserEmail(email);
		logger.info(user.getUserName()+" Logged out successfully");
		return new ResponseEntity<>("User Logged Out Successfully",HttpStatus.OK);
	}

}
