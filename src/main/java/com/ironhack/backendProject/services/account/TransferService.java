package com.ironhack.backendProject.services.account;

import com.ironhack.backendProject.dto.AccountHolderTransferDTO;
import com.ironhack.backendProject.dto.ReceiveTransferDTO;
import com.ironhack.backendProject.dto.SendTransferDTO;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.repositories.account.TransactionRepository;
import com.ironhack.backendProject.services.user.AdminService;
import com.ironhack.backendProject.services.user.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransferService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    ThirdPartyService thirdPartyService;

    //-----------------------------------THIRD PARTY SEND TRANSFER----------------------------//
    public Transaction sendTransfer(SendTransferDTO sendTransferDTO) {

        Account account = adminService.findAccountById(sendTransferDTO.getAccountId());
        if (account.getBalance().compareTo(BigDecimal.valueOf(sendTransferDTO.getAmount())) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        BigDecimal accountBalance = account.getBalance().subtract(BigDecimal.valueOf(sendTransferDTO.getAmount()));
        account.setBalance(accountBalance);
        adminService.saveAccount(account);

        Transaction transfer = new Transaction( null, account, BigDecimal.valueOf(sendTransferDTO.getAmount()));
        return transactionRepository.save(transfer);
    }



//-----------------------------------THIRD PARTY RECEIVE TRANSFER----------------------------//

    public Transaction receiveTransfer(String hashKey, ReceiveTransferDTO receiveTransferDTO) {

        thirdPartyService.getThirdPartyByHashKey(hashKey);

        Account account = adminService.findAccountById(receiveTransferDTO.getAccountId());

        if (account.getSecretKey() != receiveTransferDTO.getSecretKey()){
            throw new IllegalArgumentException("secret key not found");
        }

        BigDecimal accountBalance = account.getBalance().add(BigDecimal.valueOf(receiveTransferDTO.getAmount()));
        account.setBalance(accountBalance);
        adminService.saveAccount(account);

       Transaction transfer = new Transaction( account, null,  BigDecimal.valueOf(receiveTransferDTO.getAmount()));
        return transactionRepository.save(transfer);
    }

}
