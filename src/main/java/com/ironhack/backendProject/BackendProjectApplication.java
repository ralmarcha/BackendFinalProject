package com.ironhack.backendProject;

import com.ironhack.backendProject.repositories.RoleRepository;
import com.ironhack.backendProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendProjectApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
