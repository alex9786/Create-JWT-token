package com.example.jwt.service;

import com.example.jwt.common.UserResponse;
import com.example.jwt.dto.AdminDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.Role;

public interface RoleService {

	Role postRole(Role role);

	UserResponse userPage(UserDto userDto);

	UserResponse adminPage(AdminDto adminDto);

}
