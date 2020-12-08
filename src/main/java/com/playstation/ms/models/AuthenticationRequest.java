package com.playstation.ms.models;

import java.io.Serializable;

import org.apache.logging.log4j.util.StringBuilderFormattable;

public class AuthenticationRequest implements Serializable {
	private static final long serialVersionUID = -4396496665828865977L;
	/**
	 * 
	 */
	private String username;
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
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public AuthenticationRequest() {
	}
}
