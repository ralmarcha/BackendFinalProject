package com.ironhack.backendProject.services.user;


import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.account.TransactionRepository;
import com.ironhack.backendProject.services.account.AccountService;
import com.ironhack.backendProject.services.interfaces.AccountHolderInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AccountHolderService implements AccountHolderInt {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;

    //----------------------------CHECK OWN ACCOUNT BALANCE-----------------------------------------------------------//

    public BigDecimal checkBalance(Long accountId, Long userId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));

        if(!account.getPrimaryOwner().getId().equals(userId)){
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You don't have access for this account");
      }
        accountService.checkInterestByAccount(account);
        accountService.checkMaintenance(account);
        accountService.applyPenaltyFee(account);
        return account.getBalance();
    }

    //----------------------------------------TRANSFER----------------------------------------------------------------//
   public Transaction transfer(AccountHolderTransferDTO transferDTO) {
     Account destinationAccount = accountRepository.findById(transferDTO.getDestinationAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Destination account not found"));
     Account originAccount = accountRepository.findById(transferDTO.getOriginAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Origin account not found"));
     if(originAccount.getPrimaryOwner().getUsername().equals(transferDTO.getSenderName())){
         if (originAccount.getBalance().compareTo(transferDTO.getAmount()) < 0) {
             throw new IllegalArgumentException("Insufficient funds");
         }
         BigDecimal destinationBalance = destinationAccount.getBalance().add(transferDTO.getAmount()).setScale(2, RoundingMode.HALF_EVEN);
         destinationAccount.setBalance(destinationBalance.setScale(2, RoundingMode.HALF_EVEN));
         BigDecimal originBalance = originAccount.getBalance().subtract(transferDTO.getAmount()).setScale(2, RoundingMode.HALF_EVEN);
         originAccount.setBalance(originBalance.setScale(2, RoundingMode.HALF_EVEN));
         accountService.applyPenaltyFee(originAccount);
         accountService.saveAccount(originAccount);
         accountService.saveAccount(destinationAccount);

         Transaction transfer = new Transaction(originAccount, destinationAccount,transferDTO.getAmount().setScale(2, RoundingMode.HALF_EVEN));
         return transactionRepository.save(transfer);
     }else{
         throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Account not corresponding with owner's name");
     }
   }
}





