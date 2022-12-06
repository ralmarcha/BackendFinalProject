package com.ironhack.backendProject.services.interfaces;

import java.math.BigDecimal;

public interface AccountHolderInt {

   BigDecimal checkBalance(Long accountId, Long id);

   void transferMoney( Long originAccountId, BigDecimal amount, Long destinationAccountId);
}
