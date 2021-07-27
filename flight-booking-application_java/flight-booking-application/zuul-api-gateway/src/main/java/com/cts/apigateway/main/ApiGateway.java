package com.cts.apigateway.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableZuulServer
@EnableEurekaClient
public class ApiGateway {
	
//	@Bean
//	public CorsFilter corsFilter() {
//	   final org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
//	   final CorsConfiguration config = new CorsConfiguration();
//	   config.setAllowCredentials(true);
//	   config.addAllowedOrigin("*");
//	   config.addAllowedHeader("*");
//	   config.addAllowedMethod("OPTIONS");
//	   config.addAllowedMethod("HEAD");
//	   config.addAllowedMethod("GET");
//	   config.addAllowedMethod("PUT");
//	   config.addAllowedMethod("POST");
//	   config.addAllowedMethod("DELETE");
//	   config.addAllowedMethod("PATCH");
//	   source.registerCorsConfiguration("/**", config);
//	   return new CorsFilter(source);
//	}


	public static void main(String[] args) {
		SpringApplication.run(ApiGateway.class, args);
	}
}
