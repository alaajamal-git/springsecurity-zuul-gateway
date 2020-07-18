package com.mobileapp.api.users.shared;

import java.io.Serializable;

//this class use to produce data transfer object between presentation layer(REST controller) and Service layer

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6496205171093736492L;
	private String firatname;
	private String lastname;
	private String email;
	private String password;
	private String hashPass;
	private String userId;
	
	
	
	public String getHashPass() {
		return hashPass;
	}
	public void setHashPass(String hashPass) {
		this.hashPass = hashPass;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
