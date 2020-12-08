package com.playstation.ms.models;



public class CartLine {
	private int id;
	private Item selectedItem;
	private int quantity;
	
	public CartLine() {
	}
	
	public CartLine(int id, Item selectedItem, int quantity) {
		super();
		this.id = id;
		this.selectedItem = selectedItem;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
