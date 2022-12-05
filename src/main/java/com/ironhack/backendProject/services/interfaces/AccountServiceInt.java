package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.accountDTO.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;

public interface AccountServiceInt {
    Account createCheckingAccount(CreateCheckingAccountDTO checkingAccount);
    Account createSavingsAccount(CreateSavingsDTO savingsAccount);
    Account createCreditCard(CreateCreditCardDTO creditCardAccount);


}
