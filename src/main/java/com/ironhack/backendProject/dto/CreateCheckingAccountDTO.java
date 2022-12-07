package com.ironhack.backendProject.dto;

import com.ironhack.backendProject.enums.Status;
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


public class CreateCheckingAccountDTO extends CreateAccountDTO {
    private Status status;

    @Digits(integer=9, fraction= 2)
    private BigDecimal minimumBalance;

    public CreateCheckingAccountDTO(String secretKey, @Digits(integer = 9, fraction = 2) BigDecimal balance, AccountHolder primaryOwner, Status status, BigDecimal minimumBalance) {
        super(secretKey, balance, primaryOwner);
        this.status = status;
        this.minimumBalance = minimumBalance;
    }

    public CreateCheckingAccountDTO(String secretKey, @Digits(integer = 9, fraction = 2) BigDecimal balance, AccountHolder primaryOwner) {
        super(secretKey, balance, primaryOwner);
    }
}
