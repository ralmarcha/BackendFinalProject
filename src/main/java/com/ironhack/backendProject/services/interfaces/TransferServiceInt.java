package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.ReceiveTransferDTO;
import com.ironhack.backendProject.dto.SendTransferDTO;
import com.ironhack.backendProject.models.account.Transaction;

public interface TransferServiceInt {
    Transaction sendTransfer(String hashKey,SendTransferDTO sendTransferDTO);
    Transaction receiveTransfer(String hashKey, ReceiveTransferDTO receiveTransferDTO);
}
