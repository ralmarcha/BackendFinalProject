package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Transaction;

import java.math.BigDecimal;

public interface AccountHolderInt {

   BigDecimal checkBalance(Long accountId, Long id);
   Transaction transfer (AccountHolderTransferDTO transferDTO);


}
