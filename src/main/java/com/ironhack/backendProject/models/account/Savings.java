package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Savings extends Account {

     //TODO Interest on savings accounts is added to the account annually at the rate of specified interestRate per year.
    // That means that if I have 1000000 in a savings account with a 0.01 interest rate, 1% of 1 Million is added
    // to my account after 1 year. When a savings account balance is accessed, you must determine if it has been
    // 1 year or more since either the account was created or since interest was added to the account, and add
    // the appropriate interest to the balance if necessary.

    @DecimalMax(value="0.5")
    private BigDecimal interestRate;

    @Min(value=100)
    private BigDecimal minimumBalance;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Savings(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                   LocalDate creationDate, BigDecimal interestRate, BigDecimal minimumBalance, Status status) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
        this.status = status;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if(minimumBalance==null ){
            this.minimumBalance= new BigDecimal(1000);
        }
        else  this.minimumBalance = minimumBalance;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate==null){
            this.interestRate= new BigDecimal(0.0025);
        }
        this.interestRate = interestRate;
    }
}
