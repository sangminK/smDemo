package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDao;

@Service
public class UserService {
	
    @Autowired
    UserDao userDao;

    // 전체 조회
    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }
    
    // ID로 조회
    public Optional<User> getUser(int id) {
    	return this.userDao.findById(id);
    }

    // 데이터 추가
    public User addUser(User user) {
        return this.userDao.save(user);
    }

    // 수정
    public User updateName(int id, String name) {
    	User user = userDao.getOne(id);
    	user.setName(name);
    	return this.userDao.save(user);
    }
    
    // 수정
    public User updateSalary(int id, int salary) {
    	User user = userDao.getOne(id);
    	user.setSalary(salary);
    	return this.userDao.save(user);
    }
    
    // 삭제
    public void deleteUser(int id) {
    	this.userDao.deleteById(id);
    }

}