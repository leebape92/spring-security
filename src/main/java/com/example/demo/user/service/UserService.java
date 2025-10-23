package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
    
	
	//Stream 방식
//    public List<UserDTO> findUserList() {
//		List<UserEntity> findAllUser = userRepository.findAll();
//	
//		List<UserDTO> userDTOs = findAllUser.stream() //findAllUser 스트림으로 변환
//		        .map(UserDTO::fromUserEntity) //Entity -> DTO 변환
//		        .collect(Collectors.toList()); //변환된 값 리스트로 수집
//	
//		return userDTOs;
//    }
    
    
    //for문 방식
    public List<UserDTO> getUserList() {
    	List<UserEntity> userEntityList = userRepository.findAll();
    	List<UserDTO> result = new ArrayList<>();
    	
    	for (UserEntity data : userEntityList) {
    	    UserDTO userDTO = UserDTO.fromUserEntity(data);
    	    result.add(userDTO);
    	}

    	return result;
    }

    
    public UserDTO getUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Entity → DTO 변환
        UserDTO result = new UserDTO();
        result.setId(userEntity.getId());
        result.setUsername(userEntity.getUsername());
        result.setRole(userEntity.getRole());

        return result;
    }
    
    public void saveUser(UserDTO userDTO) {
    	System.out.println("userDTO ::: " + userDTO);
    	
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername(userDTO.getUsername());
    	userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userEntity);
        System.out.println("User saved");
    }
	

}
