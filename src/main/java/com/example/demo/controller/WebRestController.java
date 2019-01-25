package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {
	
	//@GetMapping("/hello")
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello World! 222";
	}

}

// @RestController : @ResponseBody 를 모든 메소드에서 적용해줌
//					hello 메소드의 결과 문자열을 JSON 형태로 반환
// @GetMapping : @RequestMapping(method = RequestMethod.GET) 의 축약형