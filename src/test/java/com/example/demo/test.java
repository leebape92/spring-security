package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;

@SpringBootTest // 스프링 어플리케이션 실행
@ActiveProfiles("dev")  // application-dev.yml 사용
public class test {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Test
    void saveTest() {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername("test218");
    	userEntity.setPassword(passwordEncoder.encode("1234"));
    	
        userRepository.save(userEntity);
        
        UserEntity savedUser = userRepository.findByUsername("test218").orElseThrow();
        
        assertEquals("test218", savedUser.getUsername());
    }
    
//	@Test
//	void passwordCheck() {
//		Optional<UserEntity> user = userRepository.findByUsername("lee");
//		if(user.isPresent()) {
//			System.out.println("user get ::: " + user.get());
//			UserEntity userEntity = user.get(); // Optional 내부 값 꺼내기
//			System.out.println("userEntity password ::: " + userEntity.getPassword());
//		
//			// 비밀번호 확인
//			String password = "1234";
//			boolean matches = passwordEncoder.matches(password, userEntity.getPassword());
//		
//			System.out.println("비밀번호 일치 여부: " + matches);
//		}
//		
//		UserEntity userEntity = userRepository.findByUsername("spring")
//				.orElseThrow(() -> new RuntimeException("유저 없음"));
//		System.out.println("user ::: " + userEntity.getPassword());
//		String password = "1234";
//		boolean matches = passwordEncoder.matches(password, userEntity.getPassword());
//		System.out.println("비밀번호 일치 여부: " + matches);
//	}

	
}
