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
public class CreateCreditCardDTO extends CreateAccountDTO {
    @Digits(integer=1, fraction=4)
    private BigDecimal interestRate ;

    @Digits(integer=9, fraction=2)
    private BigDecimal creditLimit;
}
