package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.security.CustomUserDetails;
import com.ironhack.backendProject.services.user.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;


//--------------------------------------GET OWN ACCOUNT BALANCE--------------------------------------------//

    @GetMapping("/check-user-balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal checkBalance(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long accountId, @RequestParam Long userId){
            return accountHolderService.checkBalance(accountId, userId);
    }

//--------------------------------------TRANSFER-----------------------------------------------------------//
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction transfer(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody  AccountHolderTransferDTO transferDTO) {
        return accountHolderService.transfer(transferDTO);
    }

}
