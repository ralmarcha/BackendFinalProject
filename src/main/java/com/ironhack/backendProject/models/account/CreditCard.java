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

//Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12) then 1% interest
// will be added to the account monthly. When the balance of a credit card is accessed, check to determine if it
// has been 1 month or more since the account was created or since interest was added,
// and if so, add the appropriate interest to the balance.
@DecimalMax("0.2")
@DecimalMin("0.1")
    private BigDecimal interestRate;
    @Min(100)
    @Max(100000)
    private BigDecimal creditLimit;

    public CreditCard(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                      LocalDate creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate==null ){
            this.interestRate= new BigDecimal(0.2);
        }
        this.interestRate = interestRate;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if(creditLimit==null ){
            this.creditLimit= new BigDecimal(100);
        }
        this.creditLimit = creditLimit;
    }
}
