package com.ironhack.backendProject.services.account;

import com.ironhack.backendProject.models.account.*;
import com.ironhack.backendProject.models.user.User;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.user.UserRepository;
import com.ironhack.backendProject.services.interfaces.AccountServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInt {
@Autowired
AccountRepository accountRepository;
@Autowired
UserRepository userRepository;


   //-------------------------------------- ADDING INTEREST RATE------------------------------------------------------//
        public void checkInterestByAccount(Account account) {

            if (account instanceof Savings) {
                if(Period.between(account.getLastUpdateDate(), LocalDate.now()).getYears()>=1){
                    account.setBalance((account.getBalance().multiply(((Savings) account).getInterestRate())).add(account.getBalance()).setScale(2, RoundingMode.HALF_EVEN));
                    accountRepository.save(account);
                }
                }
            if (account instanceof CreditCard) {
                Period period = Period.between(account.getLastUpdateDate(), LocalDate.now());

                if(period.getMonths()>=1){
                BigDecimal monthlyInterest = ((CreditCard) account).getInterestRate().divide(new BigDecimal("12"),2,RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal totalInterest = monthlyInterest.multiply(BigDecimal.valueOf(period.getMonths()));
                account.setBalance(account.getBalance().multiply(totalInterest).setScale(2, RoundingMode.HALF_EVEN).add(account.getBalance()));
                    accountRepository.save(account);
            }
            }
        }
        //-----------------------------------------MAINTENANCE--------------------------------------------------------//
        public void checkMaintenance(Account account){
            if (account instanceof Checking){
                Period period = Period.between(account.getLastUpdateDate(), LocalDate.now());
                if(period.getMonths()>=1){
                    account.setBalance(account.getBalance().subtract(((Checking) account).getMonthlyMaintenanceFee()).
                            multiply(BigDecimal.valueOf(period.getMonths())).setScale(2, RoundingMode.HALF_EVEN));
                    accountRepository.save(account);
                }
        }
        }
         //-------------------------------PENALTY FEE-----------------------------------------------------------------//
        public void applyPenaltyFee(Account account){
            if (account instanceof Checking){
                if(account.getBalance().compareTo(((Checking) account).getMinimumBalance())<0){
                    account.setBalance(account.getBalance().subtract(account.getPENALTY_FEE()));
                    accountRepository.save(account);
                }
            }
            if (account instanceof Savings){
                if(account.getBalance().compareTo(((Savings) account).getMinimumBalance())<0){
                    account.setBalance(account.getBalance().subtract(account.getPENALTY_FEE()));
                }
            }
            if (account instanceof CreditCard){
                if(account.getBalance().compareTo(new BigDecimal("0"))<0){
                   account.setBalance(account.getBalance().subtract(account.getPENALTY_FEE())) ;
                }
            }
                        if(account instanceof StudentChecking){
                if(account.getBalance().compareTo(new BigDecimal("0"))<0){
                account.setBalance(account.getBalance().subtract(account.getPENALTY_FEE())) ;
                }
            }
        }
        //-----------------------------------------save account-------------------------------------------------------//
        public Account saveAccount(Account account) {
                    return accountRepository.save(account);
       }

        //----------------------------------GET ACCOUNT BY USER ----------------------------------------------------//
        public List<Account> getAccountsByUsername(String username) {
            Optional<User> user = userRepository.findByUsername(username);
            return accountRepository.findByPrimaryOwner(user.get());
        }
   }









