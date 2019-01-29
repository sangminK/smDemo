package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

// user는 MVC 사용?!!

@RestController
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    UserService userService;
    
    // create 생성(insert)
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    // read 조회
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    // ID로 조회
    @RequestMapping(value = "/getone/{id}", method = RequestMethod.GET)
    public Optional<User> getById(@PathVariable int id) {
    	return userService.getUser(id);
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
    
    // delete 삭제
    @RequestMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable int id) {
    	userService.deleteUser(id);
    	return "delete complete";
    }
    
}