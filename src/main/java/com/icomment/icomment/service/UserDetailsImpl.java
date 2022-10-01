package com.icomment.icomment.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.icomment.icomment.domain.Rol;
import com.icomment.icomment.domain.User;


public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private final User user;

	public UserDetailsImpl(User user) {  
        this.user = user;
}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Set<SimpleGrantedAuthority> grntdAuths = new HashSet<>(); 
	     List<Rol> roles = null;   
	     if (user!=null) {
	             roles = user.getRoles();
	     }
	     if (roles!=null) {
	             for (Rol role : roles) {
	                     grntdAuths.add(new SimpleGrantedAuthority(role.getName().toString()) );  
	             }
	     }

	     return grntdAuths;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
