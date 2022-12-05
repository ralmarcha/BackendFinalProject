package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.accountDTO.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accountDTO.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

//------------------------GET ALL ACCOUNTS---------------------//
    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return accountService.getAllAccounts();
    }

//-------------------CREATE ACCOUNTS-------------------------//
@PostMapping("/create-checking-account")
@ResponseStatus(value = HttpStatus.CREATED)
public Account createCheckingAccount(@RequestBody CreateCheckingAccountDTO checkingAccount){
    return accountService.createCheckingAccount(checkingAccount);
}

@PostMapping("/create-savings-account")
@ResponseStatus(value = HttpStatus.CREATED)
public Account createSavingsAccount(@RequestBody CreateSavingsDTO savingsAccount){
    return accountService.createSavingsAccount(savingsAccount);
    }

    @PostMapping("/create-credit-card")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody CreateCreditCardDTO creditCardAccount){
        return accountService.createCreditCard(creditCardAccount);
    }


}
