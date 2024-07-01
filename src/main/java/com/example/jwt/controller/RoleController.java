package com.example.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.common.JwtToken;
import com.example.jwt.common.UserResponse;
import com.example.jwt.dto.AdminDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.Role;
import com.example.jwt.service.RoleService;

@RestController
@RequestMapping("/restApi")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	JwtToken jwtToken;
	
	@PostMapping("/userPage")
	public ResponseEntity<UserResponse> userPAge(@RequestBody UserDto userDto) {
		UserResponse userResponse = roleService.userPage(userDto);
		return ResponseEntity.status(userResponse.getStatus()).body(userResponse);
	}
	
	@PostMapping("/adminPage")
	public ResponseEntity<UserResponse> adminPAge(@RequestBody AdminDto adminDto) {
		UserResponse userResponse = roleService.adminPage(adminDto);
		return ResponseEntity.status(userResponse.getStatus()).body(userResponse);
	}
	
		

}
