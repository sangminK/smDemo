package com.example.demo.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.PostsRepository;
import com.example.demo.dto.PostsSaveRequestDto;
import com.example.demo.domain.Posts;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsRepository postsRepository;
	// -> @Autowired가 없음
	
	@RequestMapping(value = "/")
	public ModelAndView root() {
		ModelAndView mv = new ModelAndView("viewtest");
		return mv;
	}
	
	@RequestMapping(value = "/test")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView("test/test2");
		
		return mv;
	}
	
	
	//@RequestMapping(value="/hello", method=RequestMethod.GET)
	@GetMapping("/hello")
	public String hello() {
		return "Hello World! - from WebRestController";
	}
	
	// ********** insert **********************************************************
	@PostMapping("/posts/insert")
	//@RequestMapping(value="/posts", method=RequestMethod.POST)
	public void savePosts(@RequestBody PostsSaveRequestDto dto) {
		// Entity 클래스(Posts)와 controller에서 쓸 DTO는 분리해서 사용!@
		postsRepository.save(dto.toEntity());
		//return "success";
	}
	
	// ********** read all **********************************************************
	@GetMapping("/posts/read-all")
	public List<Posts> getAllPosts() {
		return postsRepository.findAll();
	}
	
	
	// ********** read one **********************************************************
	@GetMapping("/posts/read-one/{id}")
	public Optional<Posts> getOnePosts(@PathVariable long id) {
		return postsRepository.findById(id);
	}
	
	
	// ********** update **********************************************************
	@PutMapping("/posts/update-title/{id}")
	public void updateTitle(@PathVariable long id, @RequestBody PostsSaveRequestDto dto) {
		// update할 값을 인자로 보내주는 것이 아니라 insert할 때처럼
		// id 빼고 dto로 set__해줌
		// 컨트롤러에서 서비스로 dto를 넘겨주고, 거기에서 
		// ex) budget.setBudgetType(dto.getBudgetType());
		
		
		// 1. Posts에 @setter를 추가했을 때
		Posts posts = postsRepository.getOne(id);
		posts.setTitle(dto.getTitle());	
		postsRepository.save(posts);
		
		
		// 2. toBuilder 사용
//		Posts p1 = Posts.builder().build();
//		p1.toBuilder().title(dto.getTitle()).build();
//		postsRepository.save(p1);
		// https://stackoverflow.com/questions/47069561/build-an-object-from-an-existing-one-using-lombok
		
		
		
		// 3. with__() 사용

	}

	
	// ********** delete **********************************************************
	@PostMapping("/posts/delete/{id}")
	public String deletePosts(@PathVariable long id) {
		postsRepository.deleteById(id);
		return "delete Posts complete";
	}
	

}

// @RestController : @ResponseBody 를 모든 메소드에서 적용해줌
//					hello 메소드의 결과 문자열을 JSON 형태로 반환
// @GetMapping : @RequestMapping(method = RequestMethod.GET) 의 축약형

// @AllArgsConstructor : 생성자로 Bean을 주입받는데, 
//				모든 필드를 인자값으로 하는 생성자를 Lombok의 @AllArgsConstructor이 대신 생성
//				장점 -> 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움 해결

// 스프링 프레임웤에서 Bean을 주입받는 방식
// 1. @Autowired
// 2. setter
// 3. 생성자
