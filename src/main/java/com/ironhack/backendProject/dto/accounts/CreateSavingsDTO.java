package com.ironhack.backendProject.dto.accounts;

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
@AllArgsConstructor
public class CreateSavingsDTO extends CreateAccountDTO {
    private Status status;

    @Digits(integer=1, fraction=4)
    private BigDecimal interestRate;

    @Digits(integer=9, fraction=2)
    private BigDecimal minimumBalance;

    public CreateSavingsDTO(String secretKey, @Digits(integer = 9, fraction = 2) BigDecimal balance, AccountHolder primaryOwner, Status status, BigDecimal interestRate, BigDecimal minimumBalance) {
        super(secretKey, balance, primaryOwner);
        this.status = status;
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public CreateSavingsDTO(String secretKey, @Digits(integer = 9, fraction = 2) BigDecimal balance, AccountHolder primaryOwner) {
        super(secretKey, balance, primaryOwner);


    }
}
