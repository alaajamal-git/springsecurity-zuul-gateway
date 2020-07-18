package com.mobileapp.api.users.ui.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserModel {
	@NotNull(message = "first name should not be null")
	private String firatname;
	@NotNull(message = "last name should not be null")
	private String lastname;
	@Email(message = "please provide valid email")
	private String email;
	@NotNull(message = "password should not be null")
	@Size(min=7,message = "passwort should at least contain 7 characters")
	private String password;
	
	
	
	public String getFiratname() {
		return firatname;
	}
	public void setFiratname(String firatname) {
		this.firatname = firatname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
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

}
