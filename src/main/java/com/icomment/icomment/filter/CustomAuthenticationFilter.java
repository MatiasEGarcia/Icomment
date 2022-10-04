package com.icomment.icomment.filter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icomment.icomment.payload.request.LoginRequest;
import com.icomment.icomment.security.jwt.JwtUtils;
import com.icomment.icomment.service.UserDetailsImpl;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		LoginRequest login;
		
		 try {
			 login = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
	        } catch (IOException e) {
	            throw new AuthenticationServiceException(e.getMessage(), e);
	        }
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword());
		return authenticationManager.authenticate(auth);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		Map<String, String> tokens = new JwtUtils().getSuccessfulAuthTokens((UserDetailsImpl) authResult.getPrincipal(),request.getRequestURL().toString()); 
		response.setContentType(APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(),tokens);
	}


	
}
