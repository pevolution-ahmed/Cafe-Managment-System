package com.playstation.ms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private AuthUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// if cookie header is exists then g
		try {

//		String cookieHeader = request.getHeader("Cookie");
//		if (cookieHeader != null ) {
//			String authorizationHeader = cookieHeader.substring(cookieHeader.indexOf("Authorization=") + 14);
//			String username = null;
//			String jwt = null;
//			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
//				jwt = authorizationHeader.substring(6);
//				username = jwtUtil.extractUsername(jwt);
//				System.out.println("username from jwt:" + username);
//			}
			String jwtString = getJwtToken(request, true);
			if (jwtString != null) {
				String jwt = jwtString.substring(6);
				String username = jwtUtil.extractUsername(jwt);			
//				if (username != null && request.getServletContext().getAttribute("auth") == null) {
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
					System.out.println("jwtUtil.validateToken(jwt, userDetails) == "
							+ jwtUtil.validateToken(jwt, userDetails));

					if (jwtUtil.validateToken(jwt, userDetails)) {
						System.out.println("userDetails.getAuthorities() == " + userDetails.getAuthorities());
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			}

			filterChain.doFilter(request, response);
			
//			this.resetAuthenticationAfterRequest();

		} catch (ExpiredJwtException eje) {
			logger.info("Security exception for user {} - {}");
			System.out.println(eje.getClaims().getSubject() + "======" + eje.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

	private String getJwtFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			
	
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + ":::::::::::::: IS Exists!!");
			if (cookie.getName().equals("Authorization")) {
				String accessToken = cookie.getValue();
				if (accessToken == null)
					return null;

				return accessToken;
			}
		}
		}
		
		return null;
	}

	private String getJwtToken(HttpServletRequest request, boolean fromCookie) {
		if (fromCookie)
			return getJwtFromCookie(request);

		return getJwtFromRequest(request);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		return null;
	}

//	private void resetAuthenticationAfterRequest() {
//		SecurityContextHolder.getContext().setAuthentication(null);
//	}
}
