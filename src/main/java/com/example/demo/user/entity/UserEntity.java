package com.example.demo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
@Data 어노테이션이 제공하는 기능 
- @Getter: 클래스의 모든 필드에 대한 게터(getter) 메서드를 생성합니다.
- @Setter: 클래스의 모든 필드에 대한 세터(setter) 메서드를 생성합니다.
- @ToString: 클래스의 필드들을 포함하는 toString() 메서드를 생성하여 객체의 문자열 표현을 쉽게 얻을 수 있도록 합니다.
- @EqualsAndHashCode: 클래스의 필드 값을 기준으로 `equals()`와 hashCode() 메서드를 생성합니다.
- @RequiredArgsConstructor: 클래스의 final 필드에 대해서만 초기화하는 생성자를 자동으로 생성합니다.*/


@Entity
@Table(name = "tb_user")
@Data
public class UserEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;

    //boolean = 0 : false, 1 : true
    
    // 계정 잠김 여부
    @Column(nullable = false)
    private boolean locked = false;

    // 계정 활성화 여부
    @Column(nullable = false)
    private boolean enabled = true;

    // 계정 만료 여부
//    @Column(nullable = false)
//    private boolean accountNonExpired = true;

    // 비밀번호 만료 여부
//    @Column(nullable = false)
//    private boolean credentialsNonExpired = true;

}