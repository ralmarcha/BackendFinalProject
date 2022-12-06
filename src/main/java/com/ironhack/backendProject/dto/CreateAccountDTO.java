package com.ironhack.backendProject.dto;

import com.ironhack.backendProject.models.user.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreateAccountDTO {
    private String secretKey;
    private BigDecimal balance;
    private AccountHolder primaryOwner;
}
