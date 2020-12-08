package com.playstation.ms.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1089180081018428874L;
	private final String jwttoken;
	public String getJwttoken() {
		return jwttoken;
	}
	public AuthenticationResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	

	
	
}
