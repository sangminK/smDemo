package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
    public Optional<User> findById(int id) {
    	return this.userDao.findById(id);
    }
    
    // ID로 조회
    public User getOne(int id) {
    	return this.userDao.getOne(id);
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
    
    // 수정
    public User updateStatus(int id, int status) {
    	User user = userDao.getOne(id);
    	user.setStatus(status);
    	return this.userDao.save(user);
    }
    
    // 삭제
    public void deleteUser(int id) {
    	 this.userDao.deleteById(id);
    }
    
    // ***** 추가 1
    public List<User> findBySalaryOrderByNameDesc(int salary) {
    	return this.userDao.findBySalaryOrderByNameDesc(salary);
    }
    
    public List<User> findBySalaryOrderByNameAsc(int salary) {
    	return this.userDao.findBySalaryOrderByNameAsc(salary);
    }
    
    public List<User> findByStatusOrderByNameDesc(int status) {
    	return this.userDao.findByStatusOrderByNameDesc(status);
    }
    
    public List<User> findByStatusOrderByNameAsc(int status) {
    	return this.userDao.findByStatusOrderByNameAsc(status);
    }
    
    // @Query
    public List<User> selectBySalary(int salary) {
    	return this.userDao.selectBySalary(salary);
    }
    
    public List<User> selectByStatus(int status) {
    	return this.userDao.selectByStatus(status);
    }
    
    
    // ***** 추가 2
    public List<User> findBySalaryGreaterThan(int salary) {
    	return this.userDao.findBySalaryGreaterThan(salary);
    }
    
    public List<User> findBySalaryLessThan(int salary) {
    	return this.userDao.findBySalaryLessThan(salary);
    }
    
    public List<User> findBySalaryGreaterThanQuery(int salary) {
    	return this.userDao.findBySalaryGreaterThanQuery(salary);
    }
	
    public List<User> findBySalaryBetween(int start, int end) {
    	return this.userDao.findBySalaryBetween(start, end);
    }

    public List<User> findBySalaryBetweenQuery(int start, int end) {
    	return this.userDao.findBySalaryBetweenQuery(start, end);
    }
	
    
    
    //    Not Yet
    public List<User> findByNameLike(String searchTerm) {
    	return this.userDao.findByNameLike(searchTerm);
    }
    
    public List<User> getByNameContaining(String searchTerm) {
    	return this.userDao.getByNameContaining(searchTerm);
    }
    
    public List<User> readByNameStartingWith(String searchTerm) {
    	return this.userDao.readByNameStartingWith(searchTerm);
    }
    
    
    public List<User> findByNameLikeQuery(String searchTerm) {
    	return this.userDao.findByNameLikeQuery(searchTerm);
    }

}