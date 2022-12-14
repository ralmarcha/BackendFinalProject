package com.ironhack.backendProject.dto.transfers;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderTransferDTO {

    private Long originAccountId;

    private Long destinationAccountId;

    @Digits(integer=9, fraction= 2)
    private BigDecimal amount;

    private String senderName;
}
