package com.mobileapp.api.zuul.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends BasicAuthenticationFilter{

	Environment environment;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager,Environment environment) {
		super(authenticationManager);
		this.environment=environment;
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		String authorizedHeader=req.getHeader(environment.getProperty("authorize.string.token"));
		if(authorizedHeader==null || !authorizedHeader.startsWith(environment.getProperty("token.string.prefix"))) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken token=getToken(req);
		SecurityContextHolder.getContext().setAuthentication(token);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getToken(HttpServletRequest req) {
		String authorizedHeader=req.getHeader(environment.getProperty("authorize.string.token"));
		String token=authorizedHeader.replace(environment.getProperty("token.string.prefix"), "");
		String userId=Jwts.parser()
				.setSigningKey(environment.getProperty("token.secret.key"))
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		
		if(userId==null)return null;
		
		
		
		return new UsernamePasswordAuthenticationToken(userId,null, new ArrayList<>());
	}

}
