package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// user는 MVC 사용?!!

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	// for Angular
	// ================================================================
	// ================================================================

	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public ResponseEntity<?> read() {

		System.out.println("********** ********** ********** ********** user list 조회 중...");

		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);

	}

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> readOne(@PathVariable int id) {

		System.out.println("********** ********** ********** ********** user detail 조회 중...");

		final Optional<User> user = userService.findById(id);
		return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<?> addUser2(@RequestBody User userDto) {
		System.out.println("********** ********** ********** ********** user save 추가 중...");

		String name = userDto.getName();
		System.out.println("********** insert할 user name = " + name);
		int salary = userDto.getSalary();
		int status = userDto.getStatus();

		User user = new User(name, salary, status);
		this.userService.addUser(user); // save

		return new ResponseEntity<String>("insert", HttpStatus.OK);
	}

	@RequestMapping(value = "/updateng/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> modify(@PathVariable int id, @RequestBody User userDto) {

		System.out.println("********** ********** ********** ********** user update 수정 중...");

		System.out.println("ID = " + id);
		System.out.println("수정할 name = " + userDto.getName());
		System.out.println("수정할 salary = " + userDto.getSalary());
		System.out.println("수정할 status = " + userDto.getStatus());

		// name, salary, status
		this.userService.updateName(id, userDto.getName());
		this.userService.updateSalary(id, userDto.getSalary());
		this.userService.updateStatus(id, userDto.getStatus());

		return new ResponseEntity<String>("modified", HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteng/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable int id) {

		System.out.println("********** ********** ********** ********** user delete 삭제 중...");
		System.out.println("삭제 ID 값 = " + id);

		// 삭제
		userService.deleteUser(id);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	// 검색1
	@RequestMapping(value = "/salary/{salary}/order/{order}")
	public ResponseEntity<?> findBySalary1(@PathVariable("salary") int salary, @PathVariable int order) {

		System.out.println("********** ********** ********** ********** 검색 1...");

		List<User> list = null;

		// 이름 오름차순
		if (order == 1) {
			list = userService.findBySalaryOrderByNameAsc(salary);
		}
		// 이름 내림차순
		else if (order == 2) {
			list = userService.findBySalaryOrderByNameDesc(salary);
		}

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);

	}

	// 검색2
	@RequestMapping(value = "/status/{status}/order/{order}")
	public ResponseEntity<?> findByStatus1(@PathVariable("status") int status, @PathVariable int order) {

		System.out.println("********** ********** ********** ********** 검색 2...");

		List<User> list = null;

		// 이름 오름차순
		if (order == 1) {
			list = userService.findByStatusOrderByNameAsc(status);
		}
		// 이름 내림차순
		else if (order == 2) {
			list = userService.findByStatusOrderByNameDesc(status);
		}

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);

	}

	// 검색3
	@RequestMapping(value = "/salary/{salary}/greater/{greater}")
	public ResponseEntity<?> salaryGreaterLess(@PathVariable("salary") int salary, @PathVariable int greater) {

		System.out.println("********** ********** ********** ********** 검색 3...");

		List<User> list = null;

		// 이상
		if (greater == 1) {
			list = userService.findBySalaryGreaterThan(salary);
		}
		// 이하
		else if (greater == 2) {
			list = userService.findBySalaryLessThan(salary);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// 검색4
	@RequestMapping(value = "/salary/{left}/between/{right}")
	public ResponseEntity<?> salaryBetween(@PathVariable int left, @PathVariable int right) {
		System.out.println("********** ********** ********** ********** 검색 4...");

		List<User> list = null;
		list = userService.findBySalaryBetween(left, right);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// like
	@RequestMapping(value = "/like/{name}")
	public ResponseEntity<?> findByNameLike(@PathVariable String name) {
		List<User> list = null;
		list = userService.findByNameLike(name);

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// containing
	@RequestMapping(value = "/containing/{name}")
	public ResponseEntity<?> getByNameContaining(@PathVariable String name) {
		List<User> list = null;
		list = userService.getByNameContaining(name);

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// startingWith
	@RequestMapping(value = "/starting/{name}")
	public ResponseEntity<?> readByNameStartingWith(@PathVariable String name) {
		List<User> list = null;
		list = userService.readByNameStartingWith(name);

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// like Query
	@RequestMapping(value = "/likeq/{name}")
	public ResponseEntity<?> findByNameLikeQuery(@PathVariable String name) {
		List<User> list = null;
		list = userService.findByNameLikeQuery(name);

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// for JSP
	// ================================================================
	// ================================================================

	@RequestMapping("list")
	public ModelAndView getAllUsers() {

		ModelAndView mv = new ModelAndView("userList");
		List<User> list = userService.getAllUsers();
		mv.addObject("list", list);
		return mv;
	}

	// ID로 조회
	@RequestMapping(value = "/detail")
	public ModelAndView getById(@RequestParam int id) {

		ModelAndView mv = new ModelAndView("userDetail");

		User user = userService.getOne(id);

		List<User> list = new ArrayList();
		list.add(user);
		mv.addObject("user", user);

		return mv;
	}

	// create 생성(insert)
	@RequestMapping(value = "/add") // post
	public ModelAndView addNewUser(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("userList");

		// 파라미터 값 가져오기
		String name = request.getParameter("name");
		int salary = Integer.parseInt(request.getParameter("salary"));
		int status = Integer.parseInt(request.getParameter("status"));

		// user 추가
		User user = new User(name, salary, status);
		this.userService.addUser(user); // save

		// 리스트 재조회
		List<User> list = userService.getAllUsers();
		mv.addObject("list", list);

		return mv;
	}

	// 기존 JSP용 delete 삭제
	@RequestMapping(value = "/delete")
	public ModelAndView deleteById2(@RequestParam int id) {

		ModelAndView mv = new ModelAndView("userList");
		// 삭제
		userService.deleteUser(id);
		// 리스트 재조회
		List<User> list = userService.getAllUsers();
		mv.addObject("list", list);

		System.out.println("삭제 파라미터 값" + id);

		return mv;
	}

	// 무조건 salary를 바꿔야 에러가 안생김!
	@RequestMapping(value = "/update")
	public ModelAndView updateUser(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("userList");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int salary = Integer.parseInt(request.getParameter("salary"));

		this.userService.updateName(id, name);
		this.userService.updateSalary(id, salary);

		System.out.println("user update id=" + id);

		// 리스트 재조회
		List<User> list = userService.getAllUsers();
		mv.addObject("list", list);

		return mv;
	}

	// update 수정 - name 수정
	@RequestMapping(value = "/update-name/{id}/{name}", method = RequestMethod.POST)
	public User updateUserName(@PathVariable int id, @PathVariable String name) {
		return this.userService.updateName(id, name);
	}

	// update 수정 - salary 수정
	@RequestMapping(value = "/update-salary/{id}/{salary}", method = RequestMethod.POST)
	public User updateUserSalary(@PathVariable int id, @PathVariable int salary) {
		return this.userService.updateSalary(id, salary);
	}

	// userAdd.jsp 페이지 거쳐가기
	@RequestMapping(value = "/addPage")
	public ModelAndView userAddPage() {
		ModelAndView mv = new ModelAndView("userAdd");
		return mv;
	}

	@RequestMapping(value = "/jsp/like")
	public ModelAndView jspLike(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userList");

		String name = request.getParameter("like");
		System.out.println("***** **** ***** like 키워드 = " + name);

		List<User> list = userService.findByNameLike(name);
		mv.addObject("list", list);

		System.out.println("***** **** ***** like 결과 사이즈 = " + list.size());

		return mv;
	}

	@RequestMapping(value = "/jsp/containing")
	public ModelAndView jspContaining(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userList");

		String containing = request.getParameter("containing");
		List<User> list = userService.getByNameContaining(containing);
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/jsp/starting")
	public ModelAndView jspStarting(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userList");

		String starting = request.getParameter("starting");
		List<User> list = userService.readByNameStartingWith(starting);
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/jsp/likeq")
	public ModelAndView jspLikeQuery(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userList");

		String likeq = request.getParameter("likeq");
		List<User> list = userService.findByNameLikeQuery(likeq);
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/jsp/search")
	public ModelAndView jspKeywordSearch(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userList");

		String keyword = request.getParameter("keyword");
		List<User> list = userService.searchByName(keyword);
		mv.addObject("list", list);

		return mv;
	}

	// ================================================================
	// ================================================================

	// **********************************************************************************
	// @Query & 추가 조회 조건

	// 1
	// salary가 N인 User
	@RequestMapping(value = "/salary/{salary}")
	public List<User> selectBySalary(@PathVariable int salary) {
		return userService.selectBySalary(salary);
	}

	// status가 N인 User
	@RequestMapping(value = "/status/{status}")
	public List<User> selectByStatus(@PathVariable int status) {
		return userService.selectByStatus(status);
	}

	@RequestMapping(value = "/salary-search")
	public ModelAndView selectBySalary2(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("userList");

		int salary = Integer.parseInt(request.getParameter("salary"));
		int order = Integer.parseInt(request.getParameter("order"));

		System.out.println("order by = " + order);

		List<User> list = null;

		// 이름 오름차순
		if (order == 1) {
			list = userService.findBySalaryOrderByNameAsc(salary);
		}
		// 이름 내림차순
		else if (order == 2) {
			list = userService.findBySalaryOrderByNameDesc(salary);
		}

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/status-search")
	public ModelAndView selectByStatus2(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("userList");

		int status = Integer.parseInt(request.getParameter("status"));
		int order = Integer.parseInt(request.getParameter("order"));

		System.out.println("order by = " + order);

		List<User> list = null;

		// 이름 오름차순
		if (order == 1) {
			list = userService.findByStatusOrderByNameAsc(status);
		}
		// 이름 내림차순
		else if (order == 2) {
			list = userService.findByStatusOrderByNameDesc(status);
		}

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/salary-than")
	public ModelAndView salaryThan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userList");

		int salary = Integer.parseInt(request.getParameter("salary"));
		int order = Integer.parseInt(request.getParameter("order"));

		List<User> list = null;

		// 이상
		if (order == 1) {
			list = userService.findBySalaryGreaterThan(salary);
		}
		// 이하
		else if (order == 2) {
			list = userService.findBySalaryLessThan(salary);
		}

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/salary-between")
	public ModelAndView salaryBetween(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("userList");

		int left = Integer.parseInt(request.getParameter("left"));
		int right = Integer.parseInt(request.getParameter("right"));

		List<User> list = null;
		list = userService.findBySalaryBetweenQuery(left, right);

		mv.addObject("list", list);

		return mv;
	}

	// 2
	// salary가 N일때, name의 내림차순(desc)
	@RequestMapping(value = "/find-salary-order-name-desc/{salary}")
	public List<User> findBySalaryOrderByNameDesc(@PathVariable int salary) {
		return userService.findBySalaryOrderByNameDesc(salary);
	}

}