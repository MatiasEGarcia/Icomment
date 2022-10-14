package com.icomment.icomment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.icomment.icomment.filter.CustomAuthorizationFilter;
import com.icomment.icomment.util.BCPasswordEncoder;

@Configuration
@EnableScheduling
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCPasswordEncoder bcPasswordEncoder;
	
	@Autowired
	private CustomAuthorizationFilter customAuthorizationFilter;

	@Autowired /* here I enter the user's password */
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(bcPasswordEncoder.passwordEncoder());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/authC/**").permitAll().antMatchers("/commentC/admin/**").hasRole("ADMIN")
				.antMatchers("/commentC/user/**").hasAnyRole("ADMIN", "USER").antMatchers("/userC/admin/**")
				.hasRole("ADMIN").anyRequest().authenticated();
		http.apply(MyCustomDsl.customDsl());
		http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}



}
