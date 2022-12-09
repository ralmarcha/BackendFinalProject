package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.models.user.User;
import com.ironhack.backendProject.repositories.user.UserRepository;
import com.ironhack.backendProject.services.account.AccountService;
import com.ironhack.backendProject.services.user.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;


//--------------------------------------GET OWN ACCOUNT BALANCE-------------------------------------------------------//
    @GetMapping("/user/check-balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal checkBalance(@RequestParam Long accountId, @RequestParam Long userId, @AuthenticationPrincipal UserDetails userDetails){
            User user= userRepository.findByUsername(userDetails.getUsername()).get();
            return accountHolderService.checkBalance(accountId, user.getId());
    }

 //--------------------------------------GET OWN ACCOUNTS ------------------------------------------------------------//
    @GetMapping("/user/accounts")
    public List<Account> getAccounts(@AuthenticationPrincipal UserDetails userDetails){
        return accountService.getAccountsByUsername(userDetails.getUsername());
    }

//--------------------------------------TRANSFER----------------------------------------------------------------------//
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction transfer(@RequestBody  AccountHolderTransferDTO transferDTO) {
         return accountHolderService.transfer(transferDTO);
    }
}
