package com.springboot.dhtjdbcdemo.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.dhtjdbcdemo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	private static String secret = "This_is_secret";
	
	private String generateJwt(User user) {
		Date issueAt = new Date();
		
		// claims
//		Claims claims = Jwts.claims()
//				.setIssuer(user.getId().toString());
//				.setIssueAt
		// generate jwt using claims
		return "abc";
	}
}
