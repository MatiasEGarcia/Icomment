package com.icomment.icomment.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.icomment.icomment.service.UserDetailsImpl;

public class JwtUtils {
	private final String algorithmKey = "secret";
	private final Algorithm algorithm = Algorithm.HMAC256(algorithmKey.getBytes());
	
	public JwtUtils() {
		
	}
	
	public Map<String,String> getSuccessfulAuthTokens(UserDetailsImpl user, String issuer){
		Map<String, String> tokens = new HashMap<>();
		
		String accessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
				.withIssuer(issuer)
				.withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
	
		String refreshToken= JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
				.withIssuer(issuer)
				.sign(algorithm);
		
		tokens.put("accessToken",accessToken);
		tokens.put("refreshToken",refreshToken);
		return tokens;
	}
	
}
