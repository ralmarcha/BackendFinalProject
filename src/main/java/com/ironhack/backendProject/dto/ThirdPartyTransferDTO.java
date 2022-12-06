package com.ironhack.backendProject.dto;

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
public class ThirdPartyTransferDTO {

    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;

    private Long originAccountId;

    private Long destinationAccountId;

    private String secretKey;

}