package com.cybage;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.cybage.controller.UserComplaintController;
import com.cybage.service.EmailSenderService;
import com.cybage.service.ExportService;
import com.cybage.service.SendSms;
import com.cybage.service.SmsService;
import com.cybage.service.SmsServiceTwilio;


@SpringBootApplication(scanBasePackages = "com.cybage")
@EnableJpaRepositories(basePackages = "com.cybage.dao")
@EntityScan(basePackages = "com.cybage.model")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class FourR_FoodApp implements CommandLineRunner {
	@Autowired
	private EmailSenderService senderService;

	@Autowired
	private ExportService export;

	@Override
	public void run(String... args) throws Exception {
	//senderService.sendOTP("rakhid@cybage.com");

		
	}
	public static void main(String[] args) {
		SpringApplication.run(FourR_FoodApp.class, args);

	}

}
