package com.ironhack.backendProject.dto;

import com.ironhack.backendProject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSavingsDTO extends CreateAccountDTO{
    private Status status;
    private BigDecimal interestRate;
    private BigDecimal minimumBalance;
}
