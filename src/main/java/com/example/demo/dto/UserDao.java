package com.example.demo.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	// 예시
//	@Transactional
//	@Modifying
//	@Query("DELETE FROM CART_OPTION_DETAIL WHERE CART_ID = :cartId")
//	void deleteByCartId(@Param("cartId") Long cartId);
//	
//	@Transactional
//	@Modifying
//	@Query("DELETE FROM CART_OPTION_DETAIL WHERE CART_ID = :cartId AND USER_NO = :userNo")
//	void deleteByCartIdAndUserNo(@Param("cartId") Long cartId, @Param("userNo") Integer userNo);
//	
//	@Transactional
//	@Modifying
//	@Query("DELETE FROM CART_OPTION_DETAIL WHERE CART_ID IN :cartIds AND USER_NO = :userNo")
//	void deleteByCartIdsAndUserNo(@Param("cartIds") List<Long> cartIds, @Param("userNo") Integer userNo);
	
	
	
	// 단일/여러건 조회 findBy, findAllBy
	// 예시
//  List<Post> findAllByTitleLike(String title);
//  List<Post> findAllByCategory(Category category);
//  List<Post> findAllByCreatedDateGreaterThanEqualOrderByIdDesc(LocalDateTime localDateTime);
//  List<Post> findAllByTagListInOrderByCreatedDateAsc(List<Tag> tagList);
//	List<CartOptionDetail> findByCartIdOrderByOptionIdAsc(String cartId);

	
	// 1. @Query 사용(@Query, @Transactional, @Modifying)
	// 1-1. status=N 인, User
	@Transactional
	@Modifying
	@Query("SELECT u FROM User u WHERE u.status = ?1")
	List<User> selectByStatus(@Param("status") Integer status);
	
	// 1-2. 
	@Transactional
	@Modifying
	@Query("SELECT u FROM User u WHERE u.salary = ?1")
	List<User> selectBySalary(@Param("salary") Integer salary);

	
	// 2. 추가 조회 조건
	// 2-1. salary가 N일때, name의 내림차순(desc)
	List<User> findBySalaryOrderByNameDesc(Integer salary);
	
	// 2-2. 
	
}