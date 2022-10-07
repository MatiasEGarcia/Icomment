package com.icomment.icomment.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icomment.icomment.domain.ERol;
import com.icomment.icomment.domain.InvalidateToken;
import com.icomment.icomment.domain.Rol;
import com.icomment.icomment.domain.User;
import com.icomment.icomment.payload.request.LogoutRequest;
import com.icomment.icomment.payload.request.SignupRequest;
import com.icomment.icomment.payload.response.MessageResponse;
import com.icomment.icomment.security.jwt.JwtUtils;
import com.icomment.icomment.service.UserService;
import com.icomment.icomment.service.InvalidateTokenService;
import com.icomment.icomment.service.RolService;
import com.icomment.icomment.util.BCPasswordEncoder;

@RestController
@RequestMapping("/authC")
@CrossOrigin("*")
public class AuthC {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private InvalidateTokenService invalidateTokenService;
	
	@Autowired
	private BCPasswordEncoder bcPasswordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) throws Exception{
		if(userService.existsByUsername(signupRequest.getUsername())) {
			 return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if(userService.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		}
		
		List<String> setRoles = signupRequest.getRoles();
		List<Rol> roles = new ArrayList<>();
		User user = new User(signupRequest.getUsername(),
				bcPasswordEncoder.passwordEncoder().encode(signupRequest.getPassword()), signupRequest.getEmail());

		if (setRoles.isEmpty()) {
			Optional<Rol> rol = Optional.ofNullable(rolService.findByName(ERol.ROLE_USER).orElseThrow(()-> new Exception("Rol user not found")));
			roles.add(rol.get());
		} else {
			for(int i=0; i<setRoles.size();i++) {
				switch (setRoles.get(i).toLowerCase()) {
				case "user":
					Optional<Rol> userRol = Optional.ofNullable(rolService.findByName(ERol.ROLE_USER).orElseThrow(()-> new Exception("Rol user not found")));
					roles.add(userRol.get());
					break;
				case "admin":
					Optional<Rol> adminRol = Optional.ofNullable(rolService.findByName(ERol.ROLE_ADMIN).orElseThrow(()-> new Exception("Rol admin not found")));
					roles.add(adminRol.get());
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + setRoles.get(i).toLowerCase());
				}
			}
		}
		user.setRoles(roles);
		userService.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@GetMapping(value="/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String authorizationHeader= request.getHeader(AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				JwtUtils jwtUtils= new JwtUtils(authorizationHeader);
				DecodedJWT decodedJWT= jwtUtils.getDecodedJwt();
				User user = userService.getByUsername(decodedJWT.getSubject());
				Map<String, String> tokens = jwtUtils.getRefreshToken(user.getUsername(), user.getRoles(), request.getRequestURL().toString());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(),tokens);
			} catch (Exception e) {
				response.setHeader("error", e.getMessage());
				response.setStatus(FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(),error);
			}
			
		}else {
			throw new Exception("Refresh token missing");
		}
		
			
			
	}
	
	
	@GetMapping(value="/logout")
	public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) throws Exception{
		List<InvalidateToken> invTokens = new ArrayList<>();
		Date currentDate = new Date();
		invTokens.add(new InvalidateToken(logoutRequest.getAccessToken(),"accessToken", currentDate));
		invTokens.add(new InvalidateToken(logoutRequest.getRefreshToken(), "refreshToken", currentDate));
		
		invalidateTokenService.saveAll(invTokens);
		
		return ResponseEntity.ok(new MessageResponse("User logout successfully!"));
	}
	
	
}
