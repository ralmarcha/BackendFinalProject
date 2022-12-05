package com.ironhack.backendProject.dto.accountDTO;

import com.ironhack.backendProject.dto.accountDTO.CreateAccountDTO;
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
    private BigDecimal interestRate ;
    private BigDecimal creditLimit;
}