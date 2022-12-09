package com.ironhack.backendProject;

import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.user.AdminService;
import com.ironhack.backendProject.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BackendProjectApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;

	@Autowired
	AccountHolderRepository accountHolderRepository;
	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateAdminDTO createAdminDTO = new CreateAdminDTO("admin", "admin");
		userService.createAdmin(createAdminDTO);

		PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
		LocalDate date = LocalDate.of(2002,1,1);
		CreateAccountHolderDTO accountHolder = new CreateAccountHolderDTO ("Negu", "123", address, date);
		userService.createAccountHolder(accountHolder);
		//AccountHolder a =accountHolderRepository.save(userService.createAccountHolder(accountHolder));
		//CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(500), a);
		//adminService.createCheckingAccount(createAccountDTO);
}
}