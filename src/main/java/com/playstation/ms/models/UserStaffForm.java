package com.playstation.ms.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



public class UserStaffForm {
	@NotEmpty
	private String username;
	@Size(min = 6,max =20 )
	@NotEmpty
	private String password; 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
