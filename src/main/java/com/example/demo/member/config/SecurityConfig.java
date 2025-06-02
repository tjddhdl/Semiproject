package com.example.demo.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable();
//			.formLogin().disable()
//			.httpBasic().disable()
//			.authorizeHttpRequests((auth)->auth.anyRequest().permitAll());
		
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/member/login").permitAll()
				.requestMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/assets/**", "/vendor/**","/").permitAll()
				.requestMatchers("/member/listLookUp").hasAnyRole("Admin")
				.requestMatchers("/member/lookUp").hasAnyRole("Customer", "Admin")
				.requestMatchers("/member/modify").hasAnyRole("Customer")
				.requestMatchers("/member/modifyAdmin").hasAnyRole("Admin")
				.requestMatchers("/member/register").permitAll()
				.requestMatchers("/member/delete").hasAnyRole("Admin")
				.requestMatchers("/member/check-id").permitAll()
				.requestMatchers("/title/filter").permitAll()
				.requestMatchers("/title/lookUp").permitAll()
				.requestMatchers("/title/main").permitAll()
				.requestMatchers("/title/modify").hasAnyRole("Admin")
				.requestMatchers("/title/register").hasAnyRole("Admin")
				.requestMatchers("/title/search").permitAll()
				.requestMatchers("/title/delete").hasAnyRole("Admin")
				.requestMatchers("/cart/cartAdd").hasAnyRole("Customer", "Admin")
				.requestMatchers("/cart/cartDelete").hasAnyRole("Customer","Admin")
				.requestMatchers("/cart/listLookUp").hasAnyRole("Customer", "Admin")
				.requestMatchers("/cart/modify").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/orderCheck").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/buy").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/orderComplete").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/orderLookUp").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/orderDelete").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/orderCancel").hasAnyRole("Customer","Admin")
				.requestMatchers("/order/modify").hasAnyRole("Admin")
				.requestMatchers("/order/listLookUp").hasAnyRole("Admin"));

		http.formLogin(form -> form.loginPage("/member/login").loginProcessingUrl("/login").usernameParameter("id")
				.passwordParameter("password").defaultSuccessUrl("/title/main", true).permitAll());
		http.logout(logout -> logout.permitAll());
		return http.build();
	}
	/* 페이지 종류: */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
