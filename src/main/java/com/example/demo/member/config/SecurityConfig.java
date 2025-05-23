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

	private final UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/member/login").permitAll()
				.requestMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/assets/**", "/vendor/**","/").permitAll()
				.requestMatchers("/member/listLookUp").hasAnyRole("Admin").requestMatchers("/member/lookUp")
				.hasAnyRole("Customer", "Admin").requestMatchers("/member/modify").hasAnyRole("Customer", "Admin")
				.requestMatchers("/member/modifyAdmin").hasAnyRole("Admin").requestMatchers("/member/register")
				.permitAll().requestMatchers("/title/filter").permitAll().requestMatchers("/title/lookUp").permitAll()
				.requestMatchers("/title/main").permitAll().requestMatchers("/title/modify").hasAnyRole("Admin")
				.requestMatchers("/title/register").hasAnyRole("Admin").requestMatchers("/title/search").permitAll()
				.requestMatchers("/cart/cartAdd").hasAnyRole("Customer", "Admin").requestMatchers("/cart/listLookUp")
				.hasAnyRole("Customer", "Admin").requestMatchers("/order/orderCheck").hasAnyRole("Customer", "Admin"));

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

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/order/orderCheck");
	}
}
