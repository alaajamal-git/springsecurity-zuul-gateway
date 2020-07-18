package com.mobileapp.api.users.userserviceImp;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobileapp.api.users.data.UserEntity;
import com.mobileapp.api.users.data.UserRepository;
import com.mobileapp.api.users.shared.UserDto;
import com.mobileapp.api.users.userservice.UserSevice;

@Service
public class UserserviceImp implements UserSevice {

	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	public UserserviceImp(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.userRepository=userRepository;
	}
	
	@Override
	public UserDto createUser(UserDto user) {

		user.setUserId(UUID.randomUUID().toString());
		user.setHashPass(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity userEntity;
		ModelMapper mapper= new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		userEntity=mapper.map(user, UserEntity.class);
		userRepository.save(userEntity);
		
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity=this.userRepository.findByEmail(username);
		if(userEntity==null) throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(), userEntity.getHashPass(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public UserDto getUserByUsername(String email) {
		UserEntity userEntity=this.userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}
	
	

}
