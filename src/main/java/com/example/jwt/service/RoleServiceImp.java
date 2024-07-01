package com.example.jwt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.jwt.common.JwtToken;
import com.example.jwt.common.UserResponse;
import com.example.jwt.dto.AdminDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.Role;
import com.example.jwt.repository.RoleRepository;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	JwtToken jwtToken;
	
	@Override
	public Role postRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public UserResponse userPage(UserDto userDto) {
		UserResponse userResponse = new UserResponse();
		Role role = new Role();
		role.setUserName(userDto.getUserName());
		role.setFirstName(userDto.getFirstName());
		role.setLastName(userDto.getLastName());
		role.setDateOfBirth(userDto.getDateOfBirth());
		role.setPassword(userDto.getPassword());
		roleRepo.save(role);
		userResponse.setStatus(HttpStatus.OK.value());
		userResponse.setData("successfully");
		return userResponse;

	}

	@Override
	public UserResponse adminPage(AdminDto adminDto) {
		UserResponse userResponse = new UserResponse();
		Role role = roleRepo.findByUserNameAndPassword(adminDto.getUserName(), adminDto.getPassword());

		if (role == null) {
			userResponse.setData("login failed");
			userResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			userResponse.setMessege("bad request");
			return userResponse;
		} else {
			userResponse.setStatus(HttpStatus.OK.value());
			String token = jwtToken.createJWT(role);
			Map<String, Object> data = new HashMap<>();
			data.put("token created", token);
			userResponse.setData(data);
			return userResponse;

		}

	}

}
