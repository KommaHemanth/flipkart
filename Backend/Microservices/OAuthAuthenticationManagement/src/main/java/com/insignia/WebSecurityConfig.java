package com.insignia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable().antMatcher("/**").authorizeRequests().antMatchers("/", "/welcome**", "/jwtToken**")
				.permitAll().anyRequest().authenticated().and()

				.oauth2Login();

		return http.build();
	}

}
