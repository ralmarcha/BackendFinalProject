package com.ironhack.backendProject.dto.accounts;

import com.ironhack.backendProject.models.user.AccountHolder;
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
public abstract class CreateAccountDTO {
    private String secretKey;

    @Digits(integer=9, fraction= 2)
    private BigDecimal balance;

    private AccountHolder primaryOwner;
}
