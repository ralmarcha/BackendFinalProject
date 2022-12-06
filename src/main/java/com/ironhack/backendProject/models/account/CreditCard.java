package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
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
    @Digits(integer=1, fraction=4)
    private BigDecimal interestRate ;
    private BigDecimal creditLimit;

    public CreditCard(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                      BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }
    @Override
    public void setBalance(BigDecimal balance) {
        if(balance.compareTo(BigDecimal.valueOf(0))<0){
            super.setBalance(balance.subtract(super.getPENALTY_FEE())) ;
        }else{
            super.setBalance(balance);
        }
    }
  }
