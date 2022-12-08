package com.ironhack.backendProject.services;

import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Checking;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.user.AccountHolderService;
import com.ironhack.backendProject.services.user.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountHolderServiceTest {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AdminService adminService;
    @Autowired
    AccountHolderService accountHolderService;

    //--------------------------------------TRANSFER BETWEEN ACCOUNTS------------------------------------------------//
    @Test
    void transfer_Test(){
    PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
    LocalDate date = LocalDate.of(1982,5,10);
    AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
    accountHolderRepository.save(accountHolder);
    CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder);
    Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);
    CreateCheckingAccountDTO createAccountDTO2 = new CreateCheckingAccountDTO("bbb", new BigDecimal(500).setScale(2), accountHolder);
    Checking account2 = (Checking) adminService.createCheckingAccount(createAccountDTO2);

    AccountHolderTransferDTO transferDTO = new AccountHolderTransferDTO(account.getId(), account2.getId(), new BigDecimal(50).setScale(2), "Raquel");
    Transaction transfer = accountHolderService.transfer(transferDTO);

    account = (Checking) adminService.findAccountById(account.getId());
    account2 = (Checking) adminService.findAccountById(account2.getId());
    assertEquals(new BigDecimal(950).setScale(2), account.getBalance());
    assertEquals(new BigDecimal(550).setScale(2), account2.getBalance());

    }

    //------------------------------TRANSFER THROW ERROR WHEN SENDER NAME NOT EQUAL-----------------------------------//
    @Test
    void transferThrowError_whenIncorrectSender(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder);
        Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);
        CreateCheckingAccountDTO createAccountDTO2 = new CreateCheckingAccountDTO("bbb", new BigDecimal(500).setScale(2), accountHolder);
        Checking account2 = (Checking) adminService.createCheckingAccount(createAccountDTO2);

        AccountHolderTransferDTO transferDTO = new AccountHolderTransferDTO(account.getId(), account2.getId(), new BigDecimal(50).setScale(2), "Pau");

        assertThrows(IllegalArgumentException.class, () -> {accountHolderService.transfer(transferDTO);});
    }
    //----------------------------TRANSFER THROW ERROR WHEN SENDER INSUFFICIENT FUNDS---------------------------------//
    @Test
    void transferThrowError_whenInsuffFunds(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder);
        Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);
        CreateCheckingAccountDTO createAccountDTO2 = new CreateCheckingAccountDTO("bbb", new BigDecimal(500).setScale(2), accountHolder);
        Checking account2 = (Checking) adminService.createCheckingAccount(createAccountDTO2);

        AccountHolderTransferDTO transferDTO = new AccountHolderTransferDTO(account.getId(), account2.getId(), new BigDecimal(1500).setScale(2), "Pau");

        assertThrows(IllegalArgumentException.class, () -> {accountHolderService.transfer(transferDTO);});
    }
    //----------------------------CHECKBALANCE THROW ERROR WHEN NOT OWN ACCOUNT---------------------------------------//
    @Test
    void checkBalanceThrowError_whenNotMatch(){
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder1 = new AccountHolder("Raquel","123",date, address, null);
        AccountHolder accountHolder2 = new AccountHolder("Pau","123",date, address, null);
        accountHolderRepository.save(accountHolder1);
        accountHolderRepository.save(accountHolder2);
        CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder1);
        Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);

        assertThrows(IllegalArgumentException.class, () -> {accountHolderService.checkBalance(1L, 2L);});
    }
}