package com.example.demo.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
//	@Transactional
//	@Modifying
//	@Query("DELETE FROM posts WHERE id = :id AND user_id = :user_id")
//	void deleteByIdAndUserId(@Param("id") Long id, @Param("user_id") Long user_id);

	
	// 2. 추가 조회 조건
	// 2-1. salary가 N일때, user_name의 내림차순(desc)
	List<User> findBySalaryOrderByNameDesc(Integer salary);
	
	// 2-2. 
	
}