package com.ironhack.backendProject.services.user;


import com.ironhack.backendProject.dto.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.account.TransactionRepository;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.repositories.user.UserRepository;
import com.ironhack.backendProject.services.account.AccountService;
import com.ironhack.backendProject.services.interfaces.AccountHolderInt;
import com.ironhack.backendProject.services.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;
@Service
public class AccountHolderService implements AccountHolderInt {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;



    //--------------------CHECK OWN ACCOUNT BALANCE------------------------//
    @Override
    public BigDecimal checkBalance(Long accountId, Long userId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(!account.isPresent()) {
            throw new IllegalStateException("Account not found");
        }
        if(account.get().getId()!=userId){
            throw new IllegalStateException("You don't have access for this account");
        }
        accountService.checkInterestByAccount(account.get());
        accountService.checkMaintenance(account.get());
        return account.get().getBalance();
    }
    //--------------------TRANSFER------------------------//

  /*  public Transaction transfer(AccountHolderTransferDTO transferDTO) {
        Account originAccount = adminService.findAccountById(transferDTO.getOriginAccountId());
        Account destinationAccount = adminService.findAccountById(transferDTO.getDestinationAccountId());
        if (originAccount.getBalance().compareTo(BigDecimal.valueOf(transferDTO.getAmount())) < 0) {
           throw new IllegalArgumentException("Insufficient funds");
       }

        BigDecimal destinationBalance = destinationAccount.getBalance().add(BigDecimal.valueOf(transferDTO.getAmount()));
        destinationAccount.setBalance(destinationBalance);
        BigDecimal originBalance = originAccount.getBalance().subtract(BigDecimal.valueOf(transferDTO.getAmount()));
        originAccount.setBalance(originBalance);

        adminService.saveAccount(originAccount);
        adminService.saveAccount(destinationAccount);

        Transaction transfer = new Transaction(originAccount, destinationAccount,BigDecimal.valueOf(transferDTO.getAmount()));
        return transactionRepository.save(transfer);
    }*/

   public Transaction transfer (Long originAccountId, Long destinationAccountId, BigDecimal amount){
        if(!accountRepository.findById(originAccountId).isPresent() ||!accountRepository.findById(destinationAccountId).isPresent()){
            throw new IllegalStateException("Account not found"); }
     else{
             Account originAccount   = accountRepository.findById(originAccountId).get();
            originAccount.setBalance(originAccount.getBalance().subtract(amount));
            adminService.saveAccount(originAccount);
             Account destinationAccount   = accountRepository.findById(destinationAccountId).get();
              destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
              adminService.saveAccount(destinationAccount);

            Transaction transfer = new Transaction(originAccount, destinationAccount,amount);
            return transactionRepository.save(transfer);
              }
    }

}


