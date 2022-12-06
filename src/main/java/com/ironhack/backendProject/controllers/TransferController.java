package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.ReceiveTransferDTO;
import com.ironhack.backendProject.dto.SendTransferDTO;
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

    @PostMapping("/transfer/send")
    public Transaction sendExternalTransfer(@RequestBody SendTransferDTO sendTransferDTO) {
        return transferService.createExternalTransfer(sendTransferDTO);
    }

    @PostMapping("/transfer/receive")
    public Transaction receiveExternalTransfer(@RequestHeader("hash-key") String hashKey, @RequestBody ReceiveTransferDTO receiveTransferDTO) {
        return transferService.getExternalTransfer(hashKey, receiveTransferDTO);
    }
}
