package com.example.demo.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void 확인() {
		System.out.println(passwordEncoder);
	}

	@Test
	void 암호화() {
		String password1 = "1234";
		String password2 = "1234";

		String enpw1 = passwordEncoder.encode(password1);
		String enpw2 = passwordEncoder.encode(password2);

		System.out.println("암호화~: " + enpw1);
		System.out.println("암호화~: " + enpw2);

		boolean result = passwordEncoder.matches(password2, enpw2);
		System.out.println("결과~: " + result);
	}
}
