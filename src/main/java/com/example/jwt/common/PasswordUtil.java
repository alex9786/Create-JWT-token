package com.example.jwt.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

@Component
public class PasswordUtil {
	
	
	public static String passwordEncryption(String password) {

		MessageDigest algorithm;

		try {

			algorithm = MessageDigest.getInstance("SHA-1");

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-1 algorithm is not  found");
		}
		byte[] digest = algorithm.digest(password.getBytes());
		return new String(Hex.encodeHex(digest));
	}
	
	
	

}
