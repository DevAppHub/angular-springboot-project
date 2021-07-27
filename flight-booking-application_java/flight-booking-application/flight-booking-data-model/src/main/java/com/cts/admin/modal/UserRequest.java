package com.cts.admin.modal;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2653376907094693667L;

	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("mobile_number")
	private String mobileNumber;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
}
