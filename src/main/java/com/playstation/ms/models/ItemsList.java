package com.playstation.ms.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.groups.ConvertGroup;

import org.springframework.core.convert.converter.Converter;

import com.sun.istack.Nullable;

public class ItemsList {
	@Nullable
	private String newDeviceName;
	@Nullable
	private String newDeviceType;
	private List<Item> items;
	private List<Integer> quantityList;
	
	public String getNewDeviceName() {
		return newDeviceName;
	}
	public void setNewDeviceName(String newDeviceName) {
		this.newDeviceName = newDeviceName;
	}
	public String getNewDeviceType() {
		return newDeviceType;
	}
	public void setNewDeviceType(String newDeviceType) {
		this.newDeviceType = newDeviceType;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Integer> getQuantityList() {
		return quantityList;
	}
	public void setQuantityList(List<Integer> quantityList) {
		this.quantityList = quantityList;
	}
	
	
	

}
