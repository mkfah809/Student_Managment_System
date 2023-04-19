package com.spsm.decon.rightway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Decon {

	@Test
	public void shouldEncodeEncryptUserPassword() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("00");
		System.out.println("Paste this in the Db: " + encode);
	}

	@Test
	public void shouldGenerateUsername() {
		String firstName = "mina";
		String lastName = "fahmy";
		String username = firstName.charAt(0)+lastName;
		System.out.println(username);
	}
}
