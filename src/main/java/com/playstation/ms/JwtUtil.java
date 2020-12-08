package com.playstation.ms;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6914499936492303522L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String SECRET_KEY = "gP3lpWXP1rp33vzmhbrAMfPv096pchYKza0eWW4rRApmNrA2PNn43BwwZzky32ZeBixyUTqMmd7ayOCA8y2bK4xFsVbb8e2D3SKPVrFi9H";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 *JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.setHeaderParam("typ", "JWT")
		        .setIssuer("secure-api")
		        .setAudience("secure-app")
				.compact();
//		return Jwts.builder().setClaims(claims).setSubject(subject)
//		.setIssuedAt(new Date(System.currentTimeMillis()))
//		.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
//		.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS512)
//        .setHeaderParam("typ", "JWT")
//        .setIssuer("secure-api")
//        .setAudience("secure-app")
//        .compact();
//}
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
