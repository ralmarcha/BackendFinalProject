package com.ironhack.backendProject.services;

import com.ironhack.backendProject.dto.CreateAccountDTO;
import com.ironhack.backendProject.dto.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.CreateSavingsDTO;
import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.account.*;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.account.AccountService;
import com.ironhack.backendProject.services.user.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    AdminService adminService;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountService accountService;

    @BeforeEach
    void setUp() {

    }

   //-----------create checkingAccount when age>24----------------//
   @Test
   public void shouldCreateCheckingAccount_whenAgeUpper(){
       PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
       LocalDate date = LocalDate.of(2002,1,1);
       AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
       accountHolderRepository.save(accountHolder);
      CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(500), accountHolder);
       Account result = adminService.createCheckingAccount(createAccountDTO);

      assertTrue(result instanceof Checking);
   }

       //-----------create StudentAccount when age<24----------------//
   @Test
   public void shouldCreateStudentAccount_whenAgeLess(){
           PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
           LocalDate date = LocalDate.of(2002,1,1);
           AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
           accountHolderRepository.save(accountHolder);
           CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(500), accountHolder);
           Account result = adminService.createCheckingAccount(createAccountDTO);

           assertTrue(result instanceof StudentChecking);
   }

   //-----------------------create Checking Account with default values-------------------//
   @Test
   public void createCheckingAccount_shouldShowDefaultValues(){
       PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
       LocalDate date = LocalDate.of(1982,5,10);
       AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
       accountHolderRepository.save(accountHolder);
       CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(500), accountHolder);
       Checking result = (Checking) adminService.createCheckingAccount(createAccountDTO);

       assertEquals(BigDecimal.valueOf(12).setScale(2),result.getMonthlyMaintenanceFee());
       assertEquals(BigDecimal.valueOf(250),result.getMinimumBalance());

   }

   //-------------------------CREATE CREDITCARD with default values----------------------------//
@Test
     public void createCreditCard_shouldShowDefaultValues(){
    PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
    LocalDate date = LocalDate.of(1982,5,10);
    AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
    accountHolderRepository.save(accountHolder);
    CreateCreditCardDTO createCreditCardDTO = new CreateCreditCardDTO("aaa", new BigDecimal(500), accountHolder);

    CreditCard result = (CreditCard) adminService.createCreditCard(createCreditCardDTO);

    assertEquals(BigDecimal.valueOf(0.2),result.getInterestRate());
    assertEquals(BigDecimal.valueOf(100),result.getCreditLimit());
}
    //-------------------------CREATE CREDITCARD when out of accepted values----------------------------//

    @Test
    public void createCreditCard_shouldShowError(){
       PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
       LocalDate date = LocalDate.of(1982,5,10);
       AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
       accountHolderRepository.save(accountHolder);
       CreateCreditCardDTO createCreditCardDTO = new CreateCreditCardDTO("aaa", new BigDecimal(500), accountHolder,  BigDecimal.valueOf(0.05).setScale(2),BigDecimal.valueOf(1500000));

        assertThrows(IllegalArgumentException.class, () -> {adminService.createCreditCard(createCreditCardDTO);});
    }

    //-------------------------CREATE SAVINGS with default values----------------------------//
    @Test
    public void createSavings_shouldShowDefaultValues(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateSavingsDTO createSavings = new CreateSavingsDTO("aaa", new BigDecimal(500), accountHolder);

        Savings result = (Savings) adminService.createSavingsAccount(createSavings);

        assertEquals(BigDecimal.valueOf(0.0025),result.getInterestRate());
        assertEquals(BigDecimal.valueOf(1000),result.getMinimumBalance());
    }

    //-------------------------CREATE SAVINGS when out of accepted values----------------------------//

    @Test
    public void createSavings_shouldShowError(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateSavingsDTO createSavings = new CreateSavingsDTO("aaa", new BigDecimal(500), accountHolder,null,  BigDecimal.valueOf(1).setScale(2),BigDecimal.valueOf(50));

        assertThrows(IllegalArgumentException.class, () -> {adminService.createSavingsAccount(createSavings);});
    }

}
