package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.accountDTO.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.user.Admin;
import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.models.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface AdminServiceInt {

    List<User> getAllUsers();
    AccountHolder createAccountHolder(AccountHolder accountHolder);
    Admin createAdmin(Admin admin);
    ThirdParty createThirdParty(ThirdParty thirdParty);
    //void deleteUserById(Long id);
    List<Account> getAllAccounts();
    Account createCheckingAccount(CreateCheckingAccountDTO checkingAccount);
    Account createSavingsAccount(CreateSavingsDTO savingsAccount);
    Account createCreditCard(CreateCreditCardDTO creditCardAccount);
    void deleteAccountById(Long id);
    Account updateAccount(Long id, Account account);
    BigDecimal getBalance(Long id);
    BigDecimal setBalance(Long id,BigDecimal balance);
    Account findAccountById(Long id);

}
