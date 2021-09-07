package com.thinkbycode.demo.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkbycode.demo.api.SpringContext;
import com.thinkbycode.demo.api.controller.dto.UserDto;
import com.thinkbycode.demo.api.controller.dto.UserLoginDto;
import com.thinkbycode.demo.api.service.UserService;
import com.thinkbycode.demo.api.service.impl.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationMgr;

	public AuthenticationFilter(AuthenticationManager authenticationMgr) {
		super();
		this.authenticationMgr = authenticationMgr;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserLoginDto credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

			return authenticationMgr.authenticate(
					new UsernamePasswordAuthenticationToken(
							credentials.getEmail(),
							credentials.getPassword(), new ArrayList<>()
					));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String userName = ((User) authResult.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();

		UserService userService = (UserService) SpringContext.getBean("userServiceImpl");
		UserDto user = userService.getUserByEmail(userName);
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("UserId", user.getUserId());
	}

}
