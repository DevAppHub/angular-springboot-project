package com.cts.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlightBookingApplicationServer {

	public static final Logger logger=LoggerFactory.getLogger(FlightBookingApplicationServer.class);
	
	public static void main(String[] args) {
		logger.info("flight application server started...");
		SpringApplication.run(FlightBookingApplicationServer.class,args);
	}
}
