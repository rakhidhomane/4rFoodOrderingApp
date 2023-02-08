package com.cybage.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.model.ComplaintInfo;
import com.cybage.model.UserOrder;
import com.cybage.service.ComplaintInfoService;
import com.cybage.service.EmailSenderService;
import com.cybage.service.UserOrderService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/complaint")
public class UserComplaintController {
	
	static Logger logger=LogManager.getLogger(UserComplaintController.class);
	
	@Autowired
	ComplaintInfoService complaintInfoService;
	
	@Autowired
	UserOrderService userOrderService;

	@Autowired
	EmailSenderService emailService;
	
	@GetMapping("/getAllComplaintsOfUser/{userId}")
	public ResponseEntity<?> getAllUserComplaints(@PathVariable int userId){
	    List<ComplaintInfo> complaints=complaintInfoService.getAllComplaints();
	    List<ComplaintInfo> userComplaint=new ArrayList<>();
	    for(ComplaintInfo c:complaints) {
	    	if(c.getUserOrder().getUser().getUserId()==userId)
	    	   userComplaint.add(c);
	    }    
		return new ResponseEntity<>(userComplaint,HttpStatus.OK);
	}
	
	@PostMapping("/addComplaint/{orderId}")
	public ResponseEntity<?> addComplaint(@RequestBody String complaintMessage,@PathVariable int orderId){
		UserOrder userOrder=userOrderService.findUserOrderByOrderId(orderId);
		String[] messageArray = complaintMessage.split("[+ =]");
		String complaint="";
		for (String string : messageArray) {
			complaint=complaint+" "+string;
		}
		ComplaintInfo newComplaint=new ComplaintInfo();
		newComplaint.setUserOrder(userOrder);
		newComplaint.setComplaintMessage(complaint);
		newComplaint.setComplaintStatus("pending");
		newComplaint.setComplaintDate(LocalDateTime.now());
		complaintInfoService.save(newComplaint);
		logger.info("Customer "+userOrder.getUser().getUserName()+"Raise a Complaint");
		return new ResponseEntity<>("Added new Complaint",HttpStatus.OK);
	}
	
	@GetMapping("/getAllComplaintOfRestaurant/{restaurantId}")
    public ResponseEntity<?> getAllRestaurantComplaint(@PathVariable int restaurantId){
	    List<ComplaintInfo> complaints=complaintInfoService.getAllComplaints();
	    List<ComplaintInfo> restaurantComplaint=new ArrayList<>();
	    for(ComplaintInfo c:complaints) {
	    	if(c.getUserOrder().getRestaurant().getRestId()==restaurantId)
	    		restaurantComplaint.add(c);
	    }    
		return new ResponseEntity<>(restaurantComplaint,HttpStatus.OK);
	}
	
	@PutMapping("/changeComplaintStatus/{complaintId}")
	public ResponseEntity<?> changeComplaintStatus(@PathVariable int complaintId){
		ComplaintInfo complaint=complaintInfoService.findComplaintById(complaintId);
		complaint.setComplaintStatus("Solved");
		complaintInfoService.save(complaint);
		return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
	}

	@PostMapping("/send-reminder")
	public ResponseEntity<?> sendReminder(@RequestBody ComplaintInfo complaint){
		if(complaintInfoService.sendReminder(complaint)){
			System.out.println("inside controller");
			// emailService.sendOTP(complaint.getUserOrder().getRestaurant().getRestaurantEmail());
		}
		return new ResponseEntity<>(complaintInfoService.sendReminder(complaint), HttpStatus.OK);
	}
}
