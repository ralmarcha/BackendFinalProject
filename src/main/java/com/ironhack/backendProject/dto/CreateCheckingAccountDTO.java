package com.ironhack.backendProject.dto;

import com.ironhack.backendProject.enums.Status;
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
public class CreateCheckingAccountDTO extends CreateAccountDTO {
    private Status status;

    @Digits(integer=9, fraction= 2)
    private BigDecimal minimumBalance;
  }
