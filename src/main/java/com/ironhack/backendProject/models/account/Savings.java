package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
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
public class Savings extends Account {
@Digits(integer=1, fraction=4)
    private BigDecimal interestRate;
    private BigDecimal minimumBalance;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Savings(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                    BigDecimal interestRate, BigDecimal minimumBalance, Status status) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
        this.status = status;
    }
    @Override
    public void setBalance(BigDecimal balance) {
        if(balance.compareTo(minimumBalance)<0){
            super.setBalance(balance.subtract(super.getPENALTY_FEE())) ;
        }else{
            super.setBalance(balance);
        }
    }
}
