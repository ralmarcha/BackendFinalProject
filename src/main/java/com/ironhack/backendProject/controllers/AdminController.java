package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accounts.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accounts.CreateSavingsDTO;
import com.ironhack.backendProject.dto.accounts.UpdateAccountDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.user.Role;
import com.ironhack.backendProject.models.user.User;
import com.ironhack.backendProject.security.CustomUserDetails;
import com.ironhack.backendProject.services.user.AdminService;
import com.ironhack.backendProject.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;


    //-------------------------------------GET ALL ACCOUNTS------------------------------------------------//
    @GetMapping("/accounts")
    public List<Account> getAccounts(@AuthenticationPrincipal CustomUserDetails customUserDetails){
     Role role = (Role) customUserDetails.getAuthorities();
        if (role.getRole() == ROLE_ADMIN) {
            return adminService.getAllAccounts();
        }
        return adminService.getAccountsByUserId(role.getId());
    }

    //--------------------------------------CREATE ACCOUNTS------------------------------------------------//
    @PostMapping("/create-checking-account")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody CreateCheckingAccountDTO checkingAccount){
        return adminService.createCheckingAccount(checkingAccount);
    }

    @PostMapping("/create-savings-account")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createSavingsAccount(@RequestBody CreateSavingsDTO savingsAccount){
        return adminService.createSavingsAccount(savingsAccount);
    }

    @PostMapping("/create-credit-card")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody CreateCreditCardDTO creditCardAccount){
        return adminService.createCreditCard(creditCardAccount);
    }

    //-----------------------------------------DELETE ACCOUNT----------------------------------------------//
    @DeleteMapping("/delete-account/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAccountById(@PathVariable Long id){
        adminService.deleteAccountById(id);
    }

    //----------------------------------------GET ACCOUNT--------------------------------------------------//
    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@AuthenticationPrincipal CustomUserDetails customUserDetails,@PathVariable Long id) {
        return adminService.findAccountById(id);
    }

    //--------------------------------------GET BALANCE-----------------------------------------------------//
    @GetMapping("/check-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable Long id){
        return adminService.getBalance(id);
    }

    //----------------------------------------SET BALANCE---------------------------------------------------//
    @PatchMapping("/set-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account setBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        return adminService.setBalance(id,balance);
    }

    //--------------------------------------UPDATE ACCOUNT-------------------------------------------------//
    @PutMapping("/update-checking-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable Long id, @RequestBody UpdateAccountDTO accountDTO){
        return adminService.updateAccount(id,accountDTO);
    }

}
