package com.playstation.ms.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playstation.ms.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
}
