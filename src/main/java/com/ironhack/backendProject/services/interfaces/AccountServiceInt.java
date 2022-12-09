package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.models.account.Account;

import java.util.List;

public interface AccountServiceInt {
    void checkInterestByAccount(Account account);
    void checkMaintenance(Account account);
    void applyPenaltyFee(Account account);
    List<Account> getAccountsByUsername(String username);


}
