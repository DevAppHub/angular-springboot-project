package com.cts.estock.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EStockApplicationServer {

	public static final Logger logger=LoggerFactory.getLogger(EStockApplicationServer.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EStockApplicationServer.class,args);
	}
}
