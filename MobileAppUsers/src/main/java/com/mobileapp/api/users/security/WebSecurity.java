package com.mobileapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mobileapp.api.users.userservice.UserSevice;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	Environment environment;
	UserSevice userservice;
	BCryptPasswordEncoder bpasswordEncoder;
	@Autowired
	public WebSecurity(Environment environment,UserSevice userservice,BCryptPasswordEncoder bpasswordEncoder) {
		this.environment=environment;
		this.userservice=userservice;
		this.bpasswordEncoder=bpasswordEncoder;
	}
	public void configure(HttpSecurity http) throws Exception{
		//disable csrf protection 
		http.csrf().disable();
		// get http requests from zuul gateway only
		//register our Authen filter in http security
		http.authorizeRequests().antMatchers("/**").hasIpAddress(this.environment.getProperty("gateway.ip")).
		and().addFilter(getFilter());
		// to enable get into h2-console
		http.headers().frameOptions().disable();
		
	}
	public AuthenticationFilter getFilter() throws Exception {
		AuthenticationFilter authenticationFilter=new AuthenticationFilter(userservice,environment);
		authenticationFilter.setAuthenticationManager(authenticationManager());	
		//override the default "/login" path provided by spring security
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userservice).passwordEncoder(bpasswordEncoder);
		
	}

}
