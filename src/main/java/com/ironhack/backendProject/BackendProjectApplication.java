package com.ironhack.backendProject;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.account.Checking;
import com.ironhack.backendProject.models.account.Savings;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.repositories.*;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.account.CheckingRepository;
import com.ironhack.backendProject.repositories.account.CreditCardRepository;
import com.ironhack.backendProject.repositories.account.SavingsRepository;
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
	null, LocalDate.of(2022, 05, 05), new BigDecimal(250),
		 Status.ACTIVE);


		Account account2 = new Savings("abc", new BigDecimal(1000), accountHolder1,
			null, LocalDate.of(2022, 05, 05), null,null,
			 Status.ACTIVE);

         accountRepository.save(account1);
		accountRepository.save(account2);


		//----------TRANSFERS CREADAS-----------//

		//----------Admins-----------//

		//----------ThirdParty----------//


		//----------USERS-----------//
		//----------ROLES-----------//

}
}