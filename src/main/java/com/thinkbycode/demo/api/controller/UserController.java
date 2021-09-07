package com.thinkbycode.demo.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkbycode.demo.api.controller.dto.UserDetailsRequestModel;
import com.thinkbycode.demo.api.controller.dto.UserDto;
import com.thinkbycode.demo.api.controller.dto.UserResponse;
import com.thinkbycode.demo.api.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserResponse response = new UserResponse();
		
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userDetails, user);
		
		UserDto createdUser = userService.createUser(user);
		BeanUtils.copyProperties(createdUser, response);
		
		return response;
	}
	
	@GetMapping("/{userId}")
	public UserResponse getUser(@PathVariable String userId) {
		UserResponse response = new UserResponse();
		
		UserDto dbUser = userService.getUserById(userId);
		BeanUtils.copyProperties(dbUser, response);
		
		return response;
	}
	
	@PutMapping
	public String updateUser() {
		return "updateUser";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "deleteUser";
	}
}
