package com.example.demo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

// 보통 MyBatis 등에서는 Dao라고 불리는 DB layer 접근자
// JPA에선 Repository라고 부르며 인터페이스로 생성
// 단순히 인터페이스를 생성 후, JpaRepository<Entity클래스, PK타입>를 상속하면 
// 기본적인 CRUD 메소드가 자동생성

public interface PostsRepository extends JpaRepository<Posts, Long> {


}
