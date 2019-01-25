package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// main 

@RestController
@SpringBootApplication
public class SmDemoApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World! smDemo 111";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SmDemoApplication.class, args);
	}

}

// @RequestMapping : routing 정보 제공
// @RestController : 