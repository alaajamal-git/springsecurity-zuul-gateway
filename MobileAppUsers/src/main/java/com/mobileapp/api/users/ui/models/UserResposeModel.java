package com.mobileapp.api.users.ui.models;

public class UserResposeModel {
	private String firatname;
	private String lastname;
	private String email;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private String userId;
}
