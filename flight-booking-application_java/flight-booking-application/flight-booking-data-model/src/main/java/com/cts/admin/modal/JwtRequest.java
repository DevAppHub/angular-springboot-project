package com.cts.admin.modal;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6152501538391821584L;
	private String username;
	private String password;
}
