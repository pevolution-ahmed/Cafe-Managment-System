package com.playstation.ms;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.playstation.ms.models.User;
import com.playstation.ms.models.UserDetailsImplementaion;
import com.playstation.ms.services.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(()->new UsernameNotFoundException("Not found "+username));
		return user.map(UserDetailsImplementaion:: new).get();

	}
}
