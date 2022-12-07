package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.models.user.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends Account {
    @Digits(integer=1, fraction=4)
    private BigDecimal interestRate ;
    @Digits(integer=9, fraction=2)
    private BigDecimal creditLimit;

    public CreditCard(String secretKey, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                      BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

  }
