package com.playstation.ms.controllers;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.playstation.ms.AuthUserDetailsService;
import com.playstation.ms.JwtUtil;
import com.playstation.ms.models.AuthenticationRequest;
import com.playstation.ms.models.AuthenticationResponse;
import com.playstation.ms.models.User;
import com.playstation.ms.services.UserRepository;

import java.util.Date;
import java.util.List;

import javax.management.timer.TimerMBean;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationController {
	@Autowired
	UserRepository repo;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	@GetMapping("/")
	public String home() {
		User dUser = repo.findById(1).get();
		List<User> llList = repo.findAll();
		return "<h1>Welcome</h1>" + dUser.getUserName();
	}

	@GetMapping("/login")
	public String loginView(HttpServletRequest req) {	
	
		return "loginView";
	}

	@GetMapping("/staff")
	public String user(HttpServletRequest request) {
//		SecurityContextHolder.getContext().setAuthentication((Authentication) request.getServletContext().getAttribute("auth"));
		System.out.println("SECccccccccccccccccccccccccccccccccc:"+SecurityContextHolder.getContext().getAuthentication());
		return "home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "<h1>Welcome Admin</h1>";
	}

	@GetMapping("/unauthenticated")
	public String unAuthUserHanedler() {
		return "response-error";
	}
	@PostMapping("/authenticate")
	public String createAuthenticationToken(
			@RequestParam String username,
			@RequestParam String password,HttpServletResponse res)
			throws Exception {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest(username,password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse aResponse = new AuthenticationResponse(jwt);
		//TODO: Find a way to save the jwt Token in the client(SAVE WAY ONLY)
		Cookie cookie = new Cookie("Authorization", "Bearer"+jwt);
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60*2);
		res.addCookie(cookie);
		return "redirect:/staff/dashboard/devices";
	}

}
