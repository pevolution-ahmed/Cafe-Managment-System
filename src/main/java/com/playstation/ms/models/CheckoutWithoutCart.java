package com.playstation.ms.models;


public class CheckoutWithoutCart {
	private int id;
	private String numberOfPlayer;
	private String typeOfDevice;
	private CounterTime cartTime;
	private String submit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getNumberOfPlayer() {
		return numberOfPlayer;
	}
	public void setNumberOfPlayer(String numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}
	public String getTypeOfDevice() {
		return typeOfDevice;
	}
	public void setTypeOfDevice(String typeOfDevice) {
		this.typeOfDevice = typeOfDevice;
	}
	public CounterTime getCartTime() {
		return cartTime;
	}
	public void setCartTime(CounterTime cartTime) {
		this.cartTime = cartTime;
	}
	
	

}
