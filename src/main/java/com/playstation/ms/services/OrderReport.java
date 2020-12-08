package com.playstation.ms.services;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.Nullable;

@Entity
public class OrderReport {
	@Id
	@GeneratedValue
	private int id;
	@DateTimeFormat
	private Date orderTime;
	@NotEmpty
	@NotNull
	private String cashierName;
	@NotEmpty
	private String orderFor;
	@Min(1)
	private double totalPrice;
	@Nullable
	private String staffName;
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getCashierName() {
		return cashierName;
	}
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}
	public String getOrderFor() {
		return orderFor;
	}
	public void setOrderFor(String orderFor) {
		this.orderFor = orderFor;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	
}
