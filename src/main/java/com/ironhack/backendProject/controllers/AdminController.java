package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accounts.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accounts.CreateSavingsDTO;
import com.ironhack.backendProject.dto.accounts.UpdateAccountDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.user.User;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.user.UserRepository;
import com.ironhack.backendProject.services.account.AccountService;
import com.ironhack.backendProject.services.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;


    //-------------------------------------GET ALL ACCOUNTS-----------------------------------------------------------//
    @GetMapping("/accounts")
    public List<Account> getAccounts(@AuthenticationPrincipal UserDetails userDetails){
            return adminService.getAllAccounts();
     }
    //-----------------------------------------GET ALL USERS----------------------------------------------------------//
    @GetMapping("/users")
    public List<User> getUsers(){
        return adminService.getAllUsers();  }

    //--------------------------------------CREATE ACCOUNTS-----------------------------------------------------------//
    @PostMapping("/create-account/checking")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody CreateCheckingAccountDTO checkingAccount){
        return adminService.createCheckingAccount(checkingAccount);
    }

    @PostMapping("/create-account/savings")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createSavingsAccount(@RequestBody CreateSavingsDTO savingsAccount){
        return adminService.createSavingsAccount(savingsAccount);
    }

    @PostMapping("/create-account/credit")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody CreateCreditCardDTO creditCardAccount){
        return adminService.createCreditCard(creditCardAccount);
    }

    //-----------------------------------------DELETE ACCOUNT--------------------------------------------------------//
    @DeleteMapping("/delete-account/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAccountById(@PathVariable Long id){
        adminService.deleteAccountById(id);
    }

    //----------------------------------------GET ACCOUNT BY ID-------------------------------------------------------------//
    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long id) {
            return adminService.findAccountById(id);
     }

    //--------------------------------------GET BALANCE---------------------------------------------------------------//
    @GetMapping("/check-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable Long id){
        return adminService.getBalance(id);
    }

    //----------------------------------------SET BALANCE-------------------------------------------------------------//
    @PatchMapping("/set-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account setBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        return adminService.setBalance(id,balance);
    }

    //--------------------------------------UPDATE ACCOUNT------------------------------------------------------------//
    @PutMapping("/update-checking-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable Long id, @RequestBody UpdateAccountDTO accountDTO){
        return adminService.updateAccount(id,accountDTO);
    }

}
