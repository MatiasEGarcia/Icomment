package com.icomment.icomment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.icomment.icomment.util.BCPasswordEncoder;



@Configuration
public class SecurityConfig {

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private BCPasswordEncoder bcPasswordEncoder;

    @Autowired/*here I enter the user's password*/
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(bcPasswordEncoder.passwordEncoder());
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/").permitAll();

		return http.build();
	}

}
