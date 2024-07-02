package com.example.jwt.common;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.jwt.entity.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {
	
	private static long expiryDuration = 60*60;
	private static String secret = "This is secret";
	
	public String createJWT(Role role) {
		
		long  milliTime  =  System.currentTimeMillis();
		long  expiryTime =  milliTime + expiryDuration *1000;
		Date issudeAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);
		
		Claims claims = Jwts.claims().setIssuer(role.getNo().toString()).setIssuedAt(issudeAt);
		claims.setIssuedAt(issudeAt).setExpiration(expiryAt);
		
		claims.put("userName",role.getUserName());
		claims.put("firstName", role.getFirstName());
		claims.put("lastName", role.getLastName());
		claims.put("password", role.getPassword());
	
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact() ;
	}
	
	
	
	

}
