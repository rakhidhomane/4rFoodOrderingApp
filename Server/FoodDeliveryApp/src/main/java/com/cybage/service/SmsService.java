package com.cybage.service;

import com.twilio.rest.api.v2010.account.Message;

public interface SmsService {
	public Message sendSMS(String userMobile);
}
