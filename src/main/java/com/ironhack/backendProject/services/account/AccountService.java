package com.ironhack.backendProject.services.account;

import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.Checking;
import com.ironhack.backendProject.models.account.CreditCard;
import com.ironhack.backendProject.models.account.Savings;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountService {
@Autowired
    AccountRepository accountRepository;

   //-------------------------------------- ADDING INTEREST RATE-------------------------------------------------//
        public void checkInterestByAccount(Account account) {

            if (account instanceof Savings) {
                if(Period.between(account.getLastUpdateDate(), LocalDate.now()).getYears()>=1){
                    account.setBalance((account.getBalance().multiply(((Savings) account).getInterestRate())).add(account.getBalance()));
                    accountRepository.save(account);
                }
                }
            if (account instanceof CreditCard) {
                Period period = Period.between(account.getLastUpdateDate(), LocalDate.now());

                if(period.getMonths()>=1){
                    account.setBalance(account.getBalance().multiply(((CreditCard) account).
                            getInterestRate().divide(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(period.getMonths()))));

                    accountRepository.save(account);

            }    }

        }
//-------------------------------MAINTENANCE--------------------------//
        public void checkMaintenance(Account account){
            if (account instanceof Checking){
                Period period = Period.between(account.getLastUpdateDate(), LocalDate.now());
                if(period.getMonths()>=1){
                    account.setBalance(account.getBalance().add(((Checking) account).getMonthlyMaintenanceFee()).multiply(BigDecimal.valueOf(period.getMonths())));
                }
        }
        }
    }






