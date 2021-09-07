package com.thinkbycode.demo.api.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thinkbycode.demo.api.controller.dto.UserDto;
import com.thinkbycode.demo.api.db.UserRepository;
import com.thinkbycode.demo.api.jpa.entity.UserEntity;
import com.thinkbycode.demo.api.service.UserService;
import com.thinkbycode.demo.api.util.Utils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		
		entity.setEncryptedPassword(encoder.encode(user.getPassword()));
		entity.setUserId(Utils.generateUserId(30));
		
		UserEntity createdUser = userRepo.save(entity);
		
		UserDto newUser = new UserDto();
		BeanUtils.copyProperties(createdUser, newUser);
		
		return newUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity user = userRepo.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		
		return dto;
	}

	@Override
	public UserDto getUserById(String userId) {
		UserEntity dbUser = userRepo.findByUserId(userId);
		if(dbUser == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(dbUser, dto);
		
		return dto;
	}

}
