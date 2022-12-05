package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CreditCard extends Account {

@DecimalMax("0.2")
@DecimalMin("0.1")
    private BigDecimal interestRate ;
    @Min(100)
    @Max(100000)
    private BigDecimal creditLimit;

    public CreditCard(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                      LocalDate creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

}
