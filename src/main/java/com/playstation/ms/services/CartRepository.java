package com.playstation.ms.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playstation.ms.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	
}
