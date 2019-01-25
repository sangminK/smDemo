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


// 실제 DB의 테이블과 매칭될 클래스 -> Entity 클래스
// JPA를 사용하면, DB 데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 
// 이 Entity 클래스의 수정을 통해 작업

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts {

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
