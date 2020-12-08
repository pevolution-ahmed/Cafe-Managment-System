package com.playstation.ms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playstation.ms.models.Cart;

@Service
public class CartService{
	@Autowired
	private CartRepository cartRepository;
	
	public List<Cart> getAll(){
		return cartRepository.findAll();
	}
	
	public Cart getOne(int id) {
		return cartRepository.findById(id).get();
	}
	
	public Cart saveCart(Cart cartObj) {
		return cartRepository.save(cartObj);
	}
	
	public String deleteCart(int id) {
		cartRepository.deleteById(id);
		return "Cart "+id+" have been deleted";
	}
	
}
