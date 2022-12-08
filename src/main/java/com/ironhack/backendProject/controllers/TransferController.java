package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.transfers.ReceiveTransferDTO;
import com.ironhack.backendProject.dto.transfers.SendTransferDTO;
import com.ironhack.backendProject.models.account.Transaction;
import com.ironhack.backendProject.services.account.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    @Autowired
    TransferService transferService;

    //----------------------THIRD PARTY SEND TRANSFER--------------------------------//
    @PostMapping("/transfer/send")
    public Transaction sendTransfer(@RequestHeader("hash-key") String hashKey, @RequestBody SendTransferDTO sendTransferDTO) {
        return transferService.sendTransfer(hashKey,sendTransferDTO);
    }
    //----------------------THIRD PARTY RECEIVE TRANSFER--------------------------------//
    @PostMapping("/transfer/receive")
    public Transaction receiveTransfer(@RequestHeader("hash-key") String hashKey, @RequestBody ReceiveTransferDTO receiveTransferDTO) {
        return transferService.receiveTransfer(hashKey, receiveTransferDTO);
    }
}
