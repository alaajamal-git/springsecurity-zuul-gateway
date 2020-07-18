package com.mobileapp.api.users.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobileapp.api.users.shared.UserDto;
import com.mobileapp.api.users.ui.models.LogInRequestModel;
import com.mobileapp.api.users.userservice.UserSevice;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


// UsernamePasswordAuthenticationFilter is spring security class.
// in this case spring framework will try to authenticate users by calling attempauthentication method
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	UserSevice userSevice;
	Environment env;
	public AuthenticationFilter(UserSevice userSevice,Environment env) {
		this.userSevice=userSevice;
		this.env=env;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	    throws AuthenticationException {

		try {
			LogInRequestModel logInRequestModel=new ObjectMapper().readValue(request.getInputStream(), LogInRequestModel.class);
			//get AuthenticationManager that we registered in the WebSecurity class
			//and pass the incoming requests' token for authenticating the user
			//the AuthenticationManager will dynamically call the loadUserByUsername method and pass the email and get the 
			//result and compare the two token
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(logInRequestModel.getEmail(),logInRequestModel.getPassword(),new ArrayList<>()));
		}
		
		catch(IOException e) {
			throw new RuntimeException("Eroooooooooor");
		}
	}
	
	//if the authentication is successful then the spring with call successfulAuthentication method
	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
		String username=((User)auth.getPrincipal()).getUsername();
		UserDto userDto=userSevice.getUserByUsername(username);
		String userId=userDto.getUserId();
		
		String token=Jwts.builder()
					 .setSubject(userId)
					 .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.date.expired"))))
					 .signWith(SignatureAlgorithm.HS256, env.getProperty("token.secret.key"))
					 .compact();
		res.addHeader("token", token);
		res.addHeader("userid", userId);
	}
}
