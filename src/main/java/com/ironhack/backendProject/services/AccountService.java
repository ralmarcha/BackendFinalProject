package com.ironhack.backendProject.services;

import com.ironhack.backendProject.dto.accountDTO.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateSavingsDTO;
import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.account.*;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.interfaces.AccountServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AccountService implements AccountServiceInt {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


//-------------------------GET ALL ACCOUNTS--------------------------------------//
    public List<Account> getAllAccounts() {
      return accountRepository.findAll();
    }

//-------------------------CREATE ACCOUNTS-----------------------------------------//
    //------------------CREATE CHECKING ACCOUNT--------------------------------------//
    public Account createCheckingAccount(CreateCheckingAccountDTO checkingAccount) {
        if((Period.between(checkingAccount.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears())<24){
            StudentChecking studentChecking= new StudentChecking();
            studentChecking.setBalance(checkingAccount.getBalance());
            studentChecking.setCreationDate(checkingAccount.getCreationDate());
            studentChecking.setStatus(checkingAccount.getStatus());
            studentChecking.setPrimaryOwner(checkingAccount.getPrimaryOwner());
            studentChecking.setSecretKey(checkingAccount.getSecretKey());

            return accountRepository.save(studentChecking);
        }else{
           Checking checking = new Checking();
           //-------------primary owner not found-------------------//
           if(accountHolderRepository.findById(checkingAccount.getPrimaryOwner().getId()).isPresent()){
               checking.setPrimaryOwner(checkingAccount.getPrimaryOwner());
           }else{
               throw new IllegalArgumentException("Primary Owner does not exist");}

           //-------------default status active-------------------//
           if(checkingAccount.getStatus() == null)  {
               checking.setStatus(Status.ACTIVE);
           }else{
               checking.setStatus(checkingAccount.getStatus());
           }
        //-------------accepted values for minimum balance--------------------//
           if(checkingAccount.getMinimumBalance() == null){
               checking.setMinimumBalance(BigDecimal.valueOf(250));
           }else if(checkingAccount.getMinimumBalance().compareTo(BigDecimal.valueOf(250)) >= 0){
               checkingAccount.setMinimumBalance(checkingAccount.getMinimumBalance());
           } else{
               throw new IllegalArgumentException("Minimum balance can't be lower than 250");
           }

           checking.setBalance(checkingAccount.getBalance());
           checking.setCreationDate(checkingAccount.getCreationDate());
           checking.setSecretKey(checkingAccount.getSecretKey());

           return accountRepository.save(checking);

        }
    }

    //-----------CREATE SAVINGS ACCOUNT--------------------------------------//
    public Account createSavingsAccount(CreateSavingsDTO savingsAccount) {
        Savings savings = new Savings();
        //-------------primary owner not found-------------------//
        if(accountHolderRepository.findById(savingsAccount.getPrimaryOwner().getId()).isPresent()){
            savings.setPrimaryOwner(savingsAccount.getPrimaryOwner());
        }else{
            throw new IllegalArgumentException("Primary Owner does not exist");}

        //-------------accepted values for interest rate--------------------//
        if(savingsAccount.getInterestRate() == null){
            savings.setInterestRate(BigDecimal.valueOf(0.0025));
        }else if (savingsAccount.getInterestRate().compareTo(BigDecimal.valueOf(0.5)) <= 0) {
            savings.setInterestRate(savingsAccount.getInterestRate());
        }else{
            throw new IllegalArgumentException("Interest Rate can't be higher than 0.5");
        }

        //-------------accepted values for minimum balance--------------------//
        if(savingsAccount.getMinimumBalance() == null){
            savings.setMinimumBalance(BigDecimal.valueOf(1000));
        }else if(savingsAccount.getMinimumBalance().compareTo(new BigDecimal(100)) > 0){
            savings.setMinimumBalance(savingsAccount.getMinimumBalance());
        } else{
            throw new IllegalArgumentException("Minimum balance can't be lower than 100");
        }

        //-------------default status active-------------------//
         if(savingsAccount.getStatus() == null)  {
         savings.setStatus(Status.ACTIVE);
         }else{
         savings.setStatus(savingsAccount.getStatus());
         }

        savings.setBalance(savingsAccount.getBalance());
        savings.setCreationDate(savingsAccount.getCreationDate());
        savings.setSecretKey(savingsAccount.getSecretKey());

        return accountRepository.save(savings);
    }

    //-----------CREATE CREDIT CARD--------------------------------------//
    public Account createCreditCard(CreateCreditCardDTO creditCardAccount) {
        CreditCard creditCard = new CreditCard();

        //-------------primary owner not found-------------------//
        if(accountHolderRepository.findById(creditCardAccount.getPrimaryOwner().getId()).isPresent()){
            creditCard.setPrimaryOwner(creditCardAccount.getPrimaryOwner());
        }else{
            throw new IllegalArgumentException("Primary Owner does not exist");}

        //-------------accepted values for interest rate--------------------//
        if(creditCardAccount.getInterestRate() == null){
            creditCard.setInterestRate(BigDecimal.valueOf(0.2));
        }
        else if(creditCardAccount.getInterestRate().compareTo(BigDecimal.valueOf(0.1)) < 0){
            creditCard.setInterestRate(creditCardAccount.getInterestRate());
        } else{
           throw new IllegalArgumentException("Interest rate can't be lower than 0.1");
        }

        //-------------accepted values for credit limit--------------------//

        if(creditCardAccount.getCreditLimit() == null){
            creditCard.setCreditLimit(BigDecimal.valueOf(100));
        }
        else if(creditCardAccount.getCreditLimit().compareTo(BigDecimal.valueOf(100000)) < 0
                && creditCardAccount.getCreditLimit().compareTo(BigDecimal.valueOf(100)) >= 0){
                           creditCard.setCreditLimit(creditCardAccount.getCreditLimit());
            }else{
                throw new IllegalArgumentException("Credit limit can not be higher than 100000");
                        }
        creditCard.setBalance(creditCardAccount.getBalance());
        creditCard.setCreationDate(creditCardAccount.getCreationDate());
         creditCard.setSecretKey(creditCardAccount.getSecretKey());

        return accountRepository.save(creditCard);
    }

    @Override
    public void deleteAccountById(Long id) {

    }

    @Override
    public BigDecimal getBalance(Long id) {
        return null;
    }

    @Override
    public BigDecimal setBalance(Long id, BigDecimal balance) {
        return null;
    }
}
