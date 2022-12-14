package com.icomment.icomment.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static java.util.Arrays.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icomment.icomment.payload.response.MessageResponse;
import com.icomment.icomment.security.jwt.JwtUtils;
import com.icomment.icomment.service.InvalidateTokenService;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private InvalidateTokenService invalidateTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getServletPath().equals("/authC/login") || request.getServletPath().equals("/authC/refreshToken")) {
			filterChain.doFilter(request, response); //request continues
		}else {
			String authorizationHeader= request.getHeader(AUTHORIZATION);
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				try {
					if(!invalidateTokenService.existsBytoken(authorizationHeader.substring(JwtUtils.STARTS_WITH.length())) ) {
						JwtUtils jwtUtils = new JwtUtils(authorizationHeader);
						DecodedJWT decodedJWT= jwtUtils.getDecodedJwt();
						String username = decodedJWT.getSubject();
						String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
						Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
						stream(roles).forEach(role -> {
							authorities.add(new SimpleGrantedAuthority(role));
						});
						UsernamePasswordAuthenticationToken authenticationToken = 
								new UsernamePasswordAuthenticationToken(username,null,authorities);
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
						filterChain.doFilter(request, response);
					}else{
						new ObjectMapper().writeValue(response.getOutputStream(),new MessageResponse("That token was invalidated"));
					}
				} catch (Exception e) {
					response.setHeader("error", e.getMessage());
					response.setStatus(FORBIDDEN.value());
					Map<String, String> error = new HashMap<>();
					error.put("error_message", e.getMessage());
					response.setContentType(APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(),error);
				}
				
			}else {
				filterChain.doFilter(request, response); //request continues
			}
		}
		
	}

}	
