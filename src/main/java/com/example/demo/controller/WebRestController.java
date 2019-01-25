package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.PostsRepository;
import com.example.demo.dto.PostsSaveRequestDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsRepository postsRepository;
	
	
	//@RequestMapping(value="/hello", method=RequestMethod.GET)
	@GetMapping("/hello")
	public String hello() {
		return "Hello World! -from WebRestController";
	}
	
	@PostMapping("/posts")
	public void savePosts(@RequestBody PostsSaveRequestDto dto) {
		postsRepository.save(dto.toEntity());
	}

}

// @RestController : @ResponseBody 를 모든 메소드에서 적용해줌
//					hello 메소드의 결과 문자열을 JSON 형태로 반환
// @GetMapping : @RequestMapping(method = RequestMethod.GET) 의 축약형

// @AllArgsConstructor : 생성자로 Bean을 주입받는데, 
//				모든 필드를 인자값으로 하는 생성자를 Lombok의 @AllArgsConstructor이 대신 생성
//				장점 -> 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움 해결

