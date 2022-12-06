package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Transaction;
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

    @GetMapping("/check-user-balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal checkBalance(@RequestParam Long accountId, @RequestParam Long userId){
            return accountHolderService.checkBalance(accountId, userId);
    }
//------------------------------TRANSFER-----------------------------------//
    @PostMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction transfer (@PathVariable(name="id") Long originAccountId, @RequestParam Long destinationAccountId,@RequestParam BigDecimal amount) {
        return accountHolderService.transfer(originAccountId, destinationAccountId, amount);
    }

}
