package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
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
    private BigDecimal interestRate ;
    private BigDecimal creditLimit;

    public CreditCard(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                      LocalDate creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate == null){
            this.interestRate= BigDecimal.valueOf(0.2);
        }
               else if(interestRate.compareTo(BigDecimal.valueOf(0.1)) < 0){
            this.interestRate= interestRate;
        } else{
            System.out.println("Interest rate can not be lower than 0.1");
            this.interestRate = BigDecimal.valueOf(0.1);
        }
    }

    public void setCreditLimit(BigDecimal creditLimit) {
    if(creditLimit == null){
    this.creditLimit= BigDecimal.valueOf(100);
    }
       else if(creditLimit.compareTo(BigDecimal.valueOf(100000)) < 0){
            if (creditLimit.compareTo(BigDecimal.valueOf(100)) >= 0){
                this.creditLimit = creditLimit;
            }else{
                System.out.println("Credit limit can not be higher than 100000");
                this.creditLimit= BigDecimal.valueOf(100000);
            }
    }
    }
}
