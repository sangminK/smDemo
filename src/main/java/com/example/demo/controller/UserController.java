package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

// user는 MVC 사용?!!

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

//    insert 이전 버전
//    @RequestMapping(value = "/add", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody()
//    public ModelAndView addNewUser(@RequestBody User user) {

	// create 생성(insert)
	@RequestMapping(value = "/add")
	public ModelAndView addNewUser(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("userList");

		// 파라미터 값 가져오기
		String name = request.getParameter("name");
		int salary = Integer.parseInt(request.getParameter("salary"));
		int status = Integer.parseInt(request.getParameter("status"));

		// user 추가
		User user = new User(name, salary, status);
		this.userService.addUser(user);

		// 리스트 재조회
		List<User> list = userService.getAllUsers();
		mv.addObject("list", list);

		return mv;
	}

	//
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

	// read 조회
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

		User user = userService.getUser(id);

		List<User> list = new ArrayList();
		list.add(user);
		mv.addObject("user", user);

		return mv;
	}

	// delete 삭제
	@RequestMapping(value = "/delete")
	public ModelAndView deleteById(@RequestParam int id) {

		ModelAndView mv = new ModelAndView("userList");
		// 삭제
		userService.deleteUser(id);
		// 리스트 재조회
		List<User> list = userService.getAllUsers();
		mv.addObject("list", list);

		System.out.println("삭제 파라미터 값" + id);

		return mv;
	}

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

	// 2
	// salary가 N일때, name의 내림차순(desc)
	@RequestMapping(value = "/find-salary-order-name-desc/{salary}")
	public List<User> findBySalaryOrderByNameDesc(@PathVariable int salary) {
		return userService.findBySalaryOrderByNameDesc(salary);
	}

}