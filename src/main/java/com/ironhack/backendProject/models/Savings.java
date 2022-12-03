package com.ironhack.backendProject.models;

import com.ironhack.backendProject.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Savings extends Account{

//TODO Savings accounts have a default interest rate of 0.0025
//TODO Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest
// rate of 0.5
//TODO Savings accounts should have a default minimumBalance of 1000
//TODO Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
//TODO The penaltyFee for all accounts should be 40.
 //  TODO If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance
     //TODO Interest on savings accounts is added to the account annually at the rate of specified interestRate per year.
    // That means that if I have 1000000 in a savings account with a 0.01 interest rate, 1% of 1 Million is added
    // to my account after 1 year. When a savings account balance is accessed, you must determine if it has been
    // 1 year or more since either the account was created or since interest was added to the account, and add
    // the appropriate interest to the balance if necessary.
    @Digits(integer=3, fraction= 4)
    private BigDecimal interestRate;

    @Min(value=100)
    @Value(value="1000")
    private BigDecimal minimumBalance;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Savings() {
    }

    public Savings(String secretKey, BigDecimal balance, String primaryOwner, String secondaryOwner,
                   LocalDate creationDate, AccountHolder accountHolder,
                   BigDecimal interestRate, BigDecimal minimumBalance, Status status) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate, accountHolder);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
