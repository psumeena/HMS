package com.example.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.hms.controller")
@SpringBootApplication
public class HmsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HmsApplication.class, args);
	}

}
