package com.cybage.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsServiceTwilio implements SmsService{
	
	public static final String ACCOUNT_SID = "ACda20317014be738bc1da6d9cf21281be";
    public static final String AUTH_TOKEN = "b065d0b27a29a5da9de18d1b200cd357";
    
	@Override
	public Message sendSMS(String userMobile) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91"+userMobile),//The phone number you are sending text to
                new com.twilio.type.PhoneNumber("+19388008359"),//The Twilio phone number
                "Congrats your order is placed. It will be delivered shortly."
                + "Thank you for ordering with us."
                + "Have a great day")
           .create();

        return message;
	}

}
