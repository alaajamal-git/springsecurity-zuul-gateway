package com.mobileapp.api.users.userservice;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mobileapp.api.users.shared.UserDto;

public interface UserSevice extends UserDetailsService{
	
	UserDto createUser(UserDto user);

	UserDto getUserByUsername(String email);

}
