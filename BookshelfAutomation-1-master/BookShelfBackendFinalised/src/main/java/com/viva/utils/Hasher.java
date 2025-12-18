package com.viva.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.viva.BookShelfAutomation;

public class Hasher {

	public static final String SALT = "mfs-ggn-bookshelf";
	
	static ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	public static String hashingFunction(String originalPassword) throws NoSuchAlgorithmException {// Returns the
																									// hexadecimal
																									// string of hashed
																									// password
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		originalPassword += SALT;
		byte[] hashedPassword = messageDigest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));

		return toHexString(hashedPassword);

	}

	private static String toHexString(byte[] hashedPassword) {// Converts the byte array into its hexadecimal string
		BigInteger byteArray = new BigInteger(1, hashedPassword);
		StringBuilder hexString = new StringBuilder(byteArray.toString(16));
		while (hexString.length() < 32)
			hexString.insert(0, '0');
		return hexString.toString();
	}

}
