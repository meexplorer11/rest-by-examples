package com.thinkbycode.demo.api.security;

import com.thinkbycode.demo.api.SpringContext;

public interface SecurityConstants {
	long EXPIRATION_TIME = 864000000; // 10 days
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
	final String SIGN_UP_URL = "/users";
	//String TOKEN_SECRET = "o)M[DH!!o:y@wV>";
	
	public static String getTokenSecret() {
		AppProperties appProps = (AppProperties) SpringContext.getBean("appProperties");
		return appProps.getTokenSecret();
	}
	

	/*
	 * public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000; // 1 hour
	 * public static final String VERIFICATION_EMAIL_URL =
	 * "/users/email-verification"; public static final String
	 * PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request"; public static
	 * final String PASSWORD_RESET_URL = "/users/password-reset"; public static
	 * final String H2_CONSOLE = "/h2-console/**";
	 */
}
