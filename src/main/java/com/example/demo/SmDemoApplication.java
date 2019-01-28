package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// main 

@EnableJpaAuditing  // JPA Auditing 활성화
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