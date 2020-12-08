package com.playstation.ms.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

@Entity
public class Device {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String type;
	private int numOfControllers;
	@Nullable
	@OneToOne()
	private Cart cart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumOfControllers() {
		return numOfControllers;
	}
	public void setNumOfControllers(int numOfControllers) {
		this.numOfControllers = numOfControllers;
	}
	
	
}
