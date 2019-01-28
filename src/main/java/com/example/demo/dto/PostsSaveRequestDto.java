package com.example.demo.dto;

import com.example.demo.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

// Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 [기본생성자 + set메소드]를 
// 통해서만 값이 할당 -> 이때만 setter를 허용

// Entity 클래스와 거의 유사한 형태임에도 DTO클래스를 추가로 생성함
// -> 절대로 매핑되는 Entity 클래스를 Request/Response 클래스로 사용해서는 안됨
//		Entity 클래스는 가장 Core한 클래스
//		Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 Request와 Response용
//		DTO는 View를 위한 클래스라 자주 변경이 필요
//		View Layer와 DB Layer를 철저하게 역할 분리