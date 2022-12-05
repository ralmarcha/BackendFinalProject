package com.ironhack.backendProject;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.account.*;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.repositories.*;
import com.ironhack.backendProject.repositories.account.*;
import com.ironhack.backendProject.repositories.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
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
	TransactionRepository transactionRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	@Autowired
	CheckingRepository checkingRepository;

	@Autowired
	SavingsRepository savingsRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	StudentCheckingRepository studentCheckingRepository;


	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//----------AccountHolders-----------//
		PrimaryAddress primaryAddress1 = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
		//----------AccountHolders-----------//
		AccountHolder accountHolder1= new AccountHolder("Raquel","123", LocalDate.of(1982, 05, 10),primaryAddress1,
				null );
		accountHolderRepository.save(accountHolder1);
		//----------ACCOUNTS CREADAS 1 DE CADA-----------//
        Account account1 = new Checking("abc", new BigDecimal(1000), accountHolder1,
	null, LocalDate.of(2022, 05, 05),
		 Status.ACTIVE);


		Account account2 = new Savings("abc", new BigDecimal(1000), accountHolder1,
			null, LocalDate.of(2022, 05, 05), null,null,
			 Status.ACTIVE);

		Account account3 = new StudentChecking("abc", new BigDecimal(1000), accountHolder1,
				null, LocalDate.of(2022, 05, 05),
				Status.ACTIVE);


		Account account4 = new CreditCard("abc", new BigDecimal(1000), accountHolder1,
				null, LocalDate.of(2022, 05, 05), null,null);

         accountRepository.save(account1);
		accountRepository.save(account2);
		accountRepository.save(account3);
		accountRepository.save(account4);


		//----------TRANSFERS CREADAS-----------//

		//----------Admins-----------//

		//----------ThirdParty----------//


		//----------USERS-----------//
		//----------ROLES-----------//

}
}