package com.icomment.icomment.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.icomment.icomment.domain.Rol;
import com.icomment.icomment.service.UserDetailsImpl;

public class JwtUtils {
	private String authorizationHeader;
	private final String startsWith = "Bearer ";
	private final String algorithmKey = "secret";
	private final Algorithm algorithm = Algorithm.HMAC256(algorithmKey.getBytes());
	
	public JwtUtils() {
		
	}
	
	public JwtUtils(String authorizationHeader){ 
		this.authorizationHeader = authorizationHeader;
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
	
	public Map<String,String> getRefreshToken(String username,List<Rol> roles,String issuer){
		Map<String, String> tokens = new HashMap<>();
		String refresToken = authorizationHeader.substring(startsWith.length());
		
		String accessToken = JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000)) //10min
				.withIssuer(issuer)
				.withClaim("roles",roles.stream().map(rol-> rol.getName().toString()).collect(Collectors.toList()))
				.sign(algorithm);
	
		tokens.put("accessToken",accessToken);
		tokens.put("refreshToken",refresToken);
		return tokens;
	}
	
	public DecodedJWT getDecodedJwt() {
		String token = authorizationHeader.substring(startsWith.length());
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(token);
	}
	
	
}
