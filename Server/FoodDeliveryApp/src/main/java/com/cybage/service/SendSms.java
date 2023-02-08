package com.cybage.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;



@Service
public class SendSms {
//    public String sendSms() {
//    	System.out.println("Before");
//		try {
//			// Construct data
//			String apiKey = "rikP5nBYah4ezoZtcfb9WIv1uL7qswgDyGE28jm3VT0HSKQFCREMNq2hvjpGsRV0BSlLztH6OwZJc3Fd";
//			String message =  "This is your message";
//			String sender =  "FSTSMS";
//			String numbers = "918087722043";
//			
//			// Send data
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+sender+"&message="+message+"&variables_values=&flash=0&numbers="+numbers).openConnection();
//			String data = apiKey + numbers + message + sender;
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//			conn.getOutputStream().write(data.getBytes("UTF-8"));
//			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			final StringBuffer stringBuffer = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				stringBuffer.append(line);
//			}
//			System.out.println("After");
//			rd.close();
//			
//			return stringBuffer.toString();
//		} catch (Exception e) {
//			System.out.println("Error SMS "+e);
//			return "Error "+e;
//		}
//	}
	
	public static void sendSms(String message,String number) throws IOException {
		String apiKey = "rikP5nBYah4ezoZtcfb9WIv1uL7qswgDyGE28jm3VT0HSKQFCREMNq2hvjpGsRV0BSlLztH6OwZJc3Fd";
		String sender = "FastSM";
		//String number =  "8087722043";
		String lang = "english";
		String route ="p";
		try {
			URLEncoder.encode(message,"UTF-8");
			
			String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+sender+"&message="+message
					+route+"&number="+number;
			
			System.out.println(myUrl);
			
			//sending request 
			URL url = new URL(myUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			//conn.setRequestProperty(key, value);
			System.out.println("Wait ......");
			
			int code = conn.getResponseCode();		
			System.out.println("Response Code " +code);
			
			StringBuffer stringBuffer = new StringBuffer();
			final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while(true)
			{
				String line = br.readLine();
				if(line==null)
				{
					break;
				}
				stringBuffer.append(line);
				//return stringBuffer.toString();
			}
			
			System.out.println(stringBuffer);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		try {
//			SendSms.sendSms("order-Placed", "8087722043");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
