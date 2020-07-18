package com.mobileapp.api.users.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1686937392809672021L;
	@Id
	@GeneratedValue
	private long id;
	private String firatname;
	private String lastname;
	@Column(unique = true)
	private String email;
	@Column(nullable = false,unique = true)
	private String hashPass;
	@Column(nullable = false,unique = true)
	private String userId;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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


}
