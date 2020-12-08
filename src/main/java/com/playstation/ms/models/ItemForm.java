package com.playstation.ms.models;



import javax.validation.constraints.Positive;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.sun.istack.NotNull;
public class ItemForm{
	@NotNull
    @Size(min = 3, max = 20, message = "name field must be between 3 and 20 characters")
	@NotEmpty

	private String name;
	@Min(value = 1)
	@Positive
	
	private double stock;
	@Min(value = 1)
	@Positive
	private double price;
	@NotEmpty
	private String category_id;
	

	
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}


