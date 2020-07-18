package com.mobileapp.api.users.ui.controllers;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.api.users.shared.UserDto;
import com.mobileapp.api.users.ui.models.CreateUserModel;
import com.mobileapp.api.users.ui.models.UserResposeModel;
import com.mobileapp.api.users.userservice.UserSevice;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	Environment env;
	@Autowired
	UserSevice userservice;
	@GetMapping("/status/check")
	public String status() {
		
		return "it is working on port: "+env.getProperty("local.server.port");
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResposeModel> createUser(@Valid @RequestBody CreateUserModel user,Errors errors){

		//if(errors.hasErrors()) return new ResponseEntity<>(new RegistrationErr(errors),HttpStatus.BAD_REQUEST);
		ModelMapper mapper= new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userdto=mapper.map(user, UserDto.class);
		UserDto userDto=userservice.createUser(userdto);
		UserResposeModel respose=mapper.map(userDto, UserResposeModel.class);
		
		return new ResponseEntity<UserResposeModel>(respose,HttpStatus.CREATED);
		
		
		
	}
	
}
