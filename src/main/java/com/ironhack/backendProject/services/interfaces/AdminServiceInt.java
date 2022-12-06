package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.Checking;

import java.math.BigDecimal;
import java.util.List;

public interface AdminServiceInt {


    List<Account> getAllAccounts();
    Account createCheckingAccount(CreateCheckingAccountDTO checkingAccount);
    Account createSavingsAccount(CreateSavingsDTO savingsAccount);
    Account createCreditCard(CreateCreditCardDTO creditCardAccount);
    void deleteAccountById(Long id);
    BigDecimal getBalance(Long id);
    Account setBalance(Long id,BigDecimal balance);
    Account findAccountById(Long id);

}
