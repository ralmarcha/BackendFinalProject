package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.accountDTO.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceInt {

   List<Account> getAllAccounts();
    Account createCheckingAccount(CreateCheckingAccountDTO checkingAccount);
    Account createSavingsAccount(CreateSavingsDTO savingsAccount);
    Account createCreditCard(CreateCreditCardDTO creditCardAccount);
    void deleteAccountById(Long id);

    // Account updateAccount(Account account);
    BigDecimal getBalance(Long id);
    BigDecimal setBalance(Long id,BigDecimal balance);


}
