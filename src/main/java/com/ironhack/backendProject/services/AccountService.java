package com.ironhack.backendProject.services;

import com.ironhack.backendProject.dto.accountDTO.CreateAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.*;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.services.interfaces.AccountServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountService implements AccountServiceInt {

    @Autowired
    AccountRepository accountRepository;

    //-------------------------CREATE ACCOUNTS-------------------------------//

    public Account createCheckingAccount(CreateCheckingAccountDTO checkingAccount) {
       if(Period.between(checkingAccount.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears()>24){
           Checking checking = new Checking();
           checking.setBalance(checkingAccount.getBalance());
           checking.setCreationDate(checkingAccount.getCreationDate());
           checking.setStatus(checkingAccount.getStatus());
           checking.setPrimaryOwner(checkingAccount.getPrimaryOwner());
           checking.setSecretKey(checkingAccount.getSecretKey());

           return accountRepository.save(checking);
       }else{
           StudentChecking studentChecking= new StudentChecking();
           studentChecking.setBalance(checkingAccount.getBalance());
           studentChecking.setCreationDate(checkingAccount.getCreationDate());
           studentChecking.setStatus(checkingAccount.getStatus());
           studentChecking.setPrimaryOwner(checkingAccount.getPrimaryOwner());
           studentChecking.setSecretKey(checkingAccount.getSecretKey());

           return accountRepository.save(studentChecking);
       }
    }
// todo si el primary owner no lo encuentra error throw new IllegalArgumentException("Primary Owner does not exist");
    public Account createSavingsAccount(CreateSavingsDTO savingsAccount) {
        Savings savings = new Savings();

        savings.setBalance(savingsAccount.getBalance());
        savings.setCreationDate(savingsAccount.getCreationDate());
        savings.setStatus(savingsAccount.getStatus());
        savings.setPrimaryOwner(savingsAccount.getPrimaryOwner());
        savings.setSecretKey(savingsAccount.getSecretKey());

        return accountRepository.save(savings);
    }

    public Account createCreditCard(CreateCreditCardDTO creditCardAccount) {
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(creditCardAccount.getBalance());
        creditCard.setCreationDate(creditCardAccount.getCreationDate());
        creditCard.setPrimaryOwner(creditCardAccount.getPrimaryOwner());
        creditCard.setSecretKey(creditCardAccount.getSecretKey());
        creditCard.setInterestRate(creditCardAccount.getInterestRate());
        creditCard.setCreditLimit(creditCardAccount.getCreditLimit());

        return accountRepository.save(creditCard);
    }
}
