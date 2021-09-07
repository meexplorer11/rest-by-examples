package com.thinkbycode.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.thinkbycode.demo.api.security.AppProperties;

@SpringBootApplication
public class RestApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiDemoApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringContext springContext() {
		return new SpringContext();
	}
	
	@Bean
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
}
