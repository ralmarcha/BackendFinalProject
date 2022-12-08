package com.ironhack.backendProject.dto.accounts;

import com.ironhack.backendProject.dto.accounts.CreateAccountDTO;
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
public class CreateCreditCardDTO extends CreateAccountDTO {
    @Digits(integer=1, fraction=4)
    private BigDecimal interestRate ;

    @Digits(integer=9, fraction=2)
    private BigDecimal creditLimit;

    public CreateCreditCardDTO(String secretKey, @Digits(integer = 9, fraction = 2) BigDecimal balance, AccountHolder primaryOwner) {
        super(secretKey, balance, primaryOwner);
    }

    public CreateCreditCardDTO(String secretKey, @Digits(integer = 9, fraction = 2) BigDecimal balance, AccountHolder primaryOwner, BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }
}
