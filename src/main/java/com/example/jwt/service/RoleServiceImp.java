package com.example.jwt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.jwt.common.JwtToken;
import com.example.jwt.common.PasswordUtil;
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
	
	@Autowired
	PasswordUtil passwordUtil;
	
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
		role.setPassword(PasswordUtil.passwordEncryption(userDto.getPassword()));
		roleRepo.save(role);
		userResponse.setStatus(HttpStatus.OK.value());
		userResponse.setData(role);
		return userResponse;

	}

	@Override
	public UserResponse adminPage(AdminDto adminDto) {
		UserResponse userResponse = new UserResponse();
		Role role = roleRepo.findByUserNameAndPassword(adminDto.getUserName(),adminDto.getPassword());
				//PasswordUtil.passwordEncryption(adminDto.getPassword()));

		if (role == null) {
			userResponse.setData("login failed");
			userResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			userResponse.setMessage("bad request");
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

	@Override
	public UserResponse getDetail(AdminDto adminDto) {
		UserResponse userResponse = new UserResponse();
		Optional<Role> role =roleRepo.findByName(adminDto.getUserName());
		Role entity = role.get();
		String realPassword = entity.getPassword();
		String encryptionPassword =PasswordUtil.passwordEncryption(adminDto.getPassword());
		
		if(realPassword.equals(encryptionPassword)) {
			userResponse.setStatus(HttpStatus.OK.value());
			userResponse.setData(entity);
			userResponse.setMessage("the password is valid");
		}else {
			userResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			userResponse.setData("bad request");
			userResponse.setMessage("the password is unvalid");
		}
		return userResponse;
	}
	
	


}
