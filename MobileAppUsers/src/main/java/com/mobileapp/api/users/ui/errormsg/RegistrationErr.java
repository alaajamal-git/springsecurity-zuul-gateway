package com.mobileapp.api.users.ui.errormsg;

import java.util.Date;

import org.springframework.validation.Errors;

public class RegistrationErr {
	
	private Date date;
	private String message;
	public RegistrationErr(Errors errors) {

		errors.getAllErrors().forEach(x -> {
			message+=x.getDefaultMessage();
		});;

	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
