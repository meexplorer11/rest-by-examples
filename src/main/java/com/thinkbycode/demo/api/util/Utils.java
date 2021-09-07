package com.thinkbycode.demo.api.util;

import java.security.SecureRandom;
import java.util.Random;

public class Utils {
	private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    public static String generateUserId(int length) {
        return generateRandomString(length);
    }
    
    private static String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
}
