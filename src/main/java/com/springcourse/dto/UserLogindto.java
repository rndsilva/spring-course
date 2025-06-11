package com.springcourse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLogindto {
	

	@Email(message = "Invalid email adress")
	private String email;
	
	@NotBlank
	private String password;
	

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserLogindto() {
		
	}
	
	public UserLogindto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	

}
