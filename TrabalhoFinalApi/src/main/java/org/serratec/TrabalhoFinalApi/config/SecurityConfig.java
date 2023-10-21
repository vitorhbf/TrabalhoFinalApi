package org.serratec.TrabalhoFinalApi.config;

import org.serratec.TrabalhoFinalApi.Security.JwtAuthenticationFilter;
import org.serratec.TrabalhoFinalApi.Security.JwtAuthorizationFilter;
import org.serratec.TrabalhoFinalApi.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailService;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf()
				.disable();
		http.addFilter(new JwtAuthenticationFilter(this.authenticationManager(), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(this.authenticationManager(), jwtUtil, userDetailService));
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}