package com.example.demo.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/getUserList")
	public ResponseEntity<List<UserDTO>> getUserList() {
		System.out.println("@@@@@@@@@@@2");
        List<UserDTO> findUserList = userService.getUserList();
        return ResponseEntity.ok(findUserList);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<UserDTO> getUser(@RequestBody UserDTO userDTO) {
        UserDTO findUser = userService.getUser(userDTO);
        return ResponseEntity.ok(findUser);
	}
    
    @PostMapping("/saveUser")
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO) {
    	System.out.println("saveUser userDTO ::: " + userDTO);
    	
        userService.saveUser(userDTO);
        //본문 없이 200 OK 상태 코드만 반환
        return ResponseEntity.ok().build();
    }
	
}
