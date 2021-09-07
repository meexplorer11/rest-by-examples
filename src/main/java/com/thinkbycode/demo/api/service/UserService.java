package com.thinkbycode.demo.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.thinkbycode.demo.api.controller.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto getUserByEmail(String email);
	UserDto getUserById(String userId);
}
