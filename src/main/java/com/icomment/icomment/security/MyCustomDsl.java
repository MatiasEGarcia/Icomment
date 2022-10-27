package com.icomment.icomment.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import com.icomment.icomment.filter.CustomAuthenticationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>{
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        customAuthenticationFilter.setFilterProcessesUrl("/authC/login");
        customAuthenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        http.addFilter(customAuthenticationFilter);
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
}
