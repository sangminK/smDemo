package com.example.demo.domain;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.AccessLevel;



// posts ===> 게시물


// 실제 DB의 테이블과 매칭될 클래스 -> Entity 클래스
// JPA를 사용하면, DB 데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 
// 이 Entity 클래스의 수정을 통해 작업

// protected Post() { }
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}

// @Entity
//	테이블과 링크될 클래스
//	네이밍 매칭 : SalesManager.java -> sales_manager table

// @Id
//	PK 필드

// @GeneratedValue
//	PK 생성 규칙/ 기본값은 AUTO(auto_increment)
//	스프링부트 2.0에서는 옵션을 추가해야만 가능

// @Column
//	테이블의 컬럼을 나타내며, 굳이 선언하지 않아도 클래스의 필드는 모두 컬럼이 됨
//	사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을 경우

// @NoArgsConstructor, @Getter, @Builder - Lombok 라이브러리의 어노테이션
// @NoArgsConstructor
//	기본 생성자 자동 추가
//	Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, 
//			JPA에서 Entity 클래스를 생성하는 것은 허용하기 위해 추가
// @Builder 
//	해당 클래스의 빌더패턴 클래스를 생성
//	생성자 상단에 선언시, 생성자에 포함된 필드만 빌더에 포함
//	채워야할 피드가 무엇인지 명확히 지정할 수 없는 생성자와는 달리,
//	어느 필드에 어떤 값을 채워야 할지 명확하게 인지 가능


