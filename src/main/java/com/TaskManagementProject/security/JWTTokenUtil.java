package com.TaskManagementProject.security;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.TaskManagementProject.Entity.UserAuthentication;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

@Component

public class JWTTokenUtil {
	private static String secret;

private final Key key;
	
private final long expireToken = 1000L * 60 * 60 * 24;

	
	public JWTTokenUtil() {
		 secret = System.getenv("JWT_SECRET");
	        if (secret == null || secret.isEmpty()) {
	            // HS256 requires at least 32 bytes (256 bits). Make sure the string is long enough.
	            secret = "ReplaceThisWithAVeryVerySecretKeyOfAtLeast32Bytes!";
	        }

	        // use StandardCharsets to avoid platform differences
	        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	    }

	
	public String generateToken(UserAuthentication user) {
		Date now = new Date();
		Date expiry = new Date(now.getTime()+expireToken);
		
		return Jwts.builder()
				.setSubject(user.getUserOfficialEmail())
				.setIssuedAt(now)
				.setExpiration(expiry)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean validToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
			
		} catch (JwtException e) {
			return false;
			
		}
	}
	
	public String extractUserEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}


}
