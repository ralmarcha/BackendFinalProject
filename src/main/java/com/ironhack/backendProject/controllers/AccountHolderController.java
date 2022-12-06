package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.services.user.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;


//---------------------------GET OWN ACCOUNT BALANCE-----------------------//

    @GetMapping("/check-user-balance/")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal checkBalance(@RequestParam Long accountId, @RequestParam Long userId){
            return accountHolderService.checkBalance(accountId, userId);
    }
//------------------------------TRANSFER-----------------------------------//
    @PatchMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void transferMoney( @PathVariable(name = "id") Long originAccountId,@RequestParam BigDecimal amount,@RequestParam Long destinationAccountId)  {
      accountHolderService.transferMoney( originAccountId, amount, destinationAccountId) ;
    }

}
