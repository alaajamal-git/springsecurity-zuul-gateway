package com.mobileapp.api.zuul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	Environment env;
	@Autowired
	public WebSecurity(Environment env) {

		this.env=env;

	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
		//disable csrf protection 
		http.csrf().disable();
		// get http requests from zuul gateway only
		//register our Authen filter in http security
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,env.getProperty("url.signup.path")).permitAll()
		.antMatchers(HttpMethod.POST,env.getProperty("url.signin.path")).permitAll()
		.antMatchers(HttpMethod.POST,env.getProperty("url.h2-console.path")).permitAll()
		.anyRequest().authenticated().and()
		.addFilter(new AuthenticationFilter(authenticationManager(), this.env));
		// to enable get into h2-console
		http.headers().frameOptions().disable();
		
	}
	
}
