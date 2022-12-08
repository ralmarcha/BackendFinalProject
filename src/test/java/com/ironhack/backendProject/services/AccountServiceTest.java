package com.ironhack.backendProject.services;

import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accounts.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accounts.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.CreditCard;
import com.ironhack.backendProject.models.account.Savings;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.account.AccountService;
import com.ironhack.backendProject.services.user.AccountHolderService;
import com.ironhack.backendProject.services.user.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AdminService adminService;
    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    AccountRepository accountRepository;

//--------------------------------------------PENALTY FEE IS APPLIED-------------------------------------------------//
    @Test
    public void penaltyFeeIsApplied_WhenMinimumBalance(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,1,1);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);

        CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(200), accountHolder);
        Account result = adminService.createCheckingAccount(createAccountDTO);

        assertEquals(new BigDecimal(160).setScale(2),  accountHolderService.checkBalance(1L, 1L));
    }
//-----------------------------------------MAINTENANCE FEE IS APPLIED-------------------------------------------------//
@Test
public void maintenanceFeeIsApplied_WhenPeriod(){
    PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
    LocalDate date = LocalDate.of(1982,1,1);
    AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
    accountHolderRepository.save(accountHolder);

    CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(500), accountHolder);
    Account result = adminService.createCheckingAccount(createAccountDTO);
    result.setLastUpdateDate(LocalDate.of(2022,11,6));
    accountRepository.save(result);

    assertEquals(new BigDecimal(488).setScale(2),  accountHolderService.checkBalance(1L, 1L));
}
    //---------------------------INTEREST RATE CREDIT CARD IS APPLIED-------------------------------------------------//
    @Test
    public void interestRateIsApplied_WhenPeriod(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,1,1);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);

        CreateCreditCardDTO createCreditCardDTO = new CreateCreditCardDTO("aaa", new BigDecimal(1000), accountHolder);
        CreditCard result = (CreditCard) adminService.createCreditCard(createCreditCardDTO);
        result.setLastUpdateDate(LocalDate.of(2022,11,6));
        accountRepository.save(result);

        //default 0.2/12*1000=20 in 1 month
        assertEquals(new BigDecimal(1020.00).setScale(2, RoundingMode.HALF_EVEN),  accountHolderService.checkBalance(1L, 1L));
         }
    //------------------------------INTEREST RATE SAVINGS  IS APPLIED-------------------------------------------------//
    @Test
    public void interestRateSavingsIsApplied_WhenPeriod(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,1,1);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);

        CreateSavingsDTO createSavings = new CreateSavingsDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder);
        Savings result = (Savings) adminService.createSavingsAccount(createSavings);
        result.setLastUpdateDate(LocalDate.of(2021,12,6));
        accountRepository.save(result);
        //default 0.0025*1000=2.5 in 1 year
        assertEquals(new BigDecimal(1002.50).setScale(2, RoundingMode.HALF_EVEN),  accountHolderService.checkBalance(1L, 1L));
    }
}