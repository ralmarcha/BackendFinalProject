package com.ironhack.backendProject.dto.accountDTO;

import com.ironhack.backendProject.models.user.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreateAccountDTO {
    private String secretKey;
    private BigDecimal balance;
    private AccountHolder primaryOwner;
    private LocalDate creationDate;

}
