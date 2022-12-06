package com.ironhack.backendProject.services.user;


import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.account.TransactionRepository;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.repositories.user.UserRepository;
import com.ironhack.backendProject.services.interfaces.AccountHolderInt;
import com.ironhack.backendProject.services.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;
@Service
public class AccountHolderService implements AccountHolderInt {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    TransactionRepository transactionRepository;


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
        //CHECKINTERESTBYACCOUNT(ACCOUNT.GET())
        //CHECKMAINTENANCE(ACCOUNT.GET())
        return account.get().getBalance();
    }
    //--------------------TRANSFER------------------------//

    public void transferMoney( Long originAccountId, BigDecimal amount, Long destinationAccountId) {

        Account originAccount = accountRepository.findById(originAccountId).get();
       // if(userRepository.findByUsername(userName).isPresent()) {
        //    User user = userRepository.findByUsername(userName).get();

            if(originAccount.getBalance().compareTo(amount)< 0){
                throw new IllegalArgumentException("Insufficient funds");
            }
            Account destinationAccount = accountRepository.findById(destinationAccountId).get();

                destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
                originAccount.setBalance(originAccount.getBalance().subtract(amount));
                  accountRepository.save(originAccount);
                    accountRepository.save(destinationAccount);
                   }
      // throw  new IllegalArgumentException("Username does not exist");
   }
