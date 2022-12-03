package com.ironhack.backendProject;

import com.ironhack.backendProject.models.Account;
import com.ironhack.backendProject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackendProjectApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//----------ACCOUNTS CREADAS 1 DE CADA-----------//
		//----------TRANSFERS CREADAS-----------//
		//----------address-----------//
		//----------AccountHolders-----------//
		//----------Admins-----------//
		//----------ThirdParty----------//
		//----------USERS-----------//
		//----------ROLES-----------//

}
}