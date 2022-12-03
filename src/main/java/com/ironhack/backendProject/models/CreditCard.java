package com.ironhack.backendProject.models;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditCard extends Account{
    //TODO CreditCard accounts have a default creditLimit of 100
    //TODO CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
    //TODO CreditCards have a default interestRate of 0.2
    //TODO CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1
    //The penaltyFee for all accounts should be 40.
    //If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically
//Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12) then 1% interest
// will be added to the account monthly. When the balance of a credit card is accessed, check to determine if it
// has been 1 month or more since the account was created or since interest was added,
// and if so, add the appropriate interest to the balance.

    private BigDecimal interestRate;
    private BigDecimal creditLimit;

    public CreditCard() {
    }

    public CreditCard(String secretKey, BigDecimal balance, String primaryOwner, String secondaryOwner,
                      LocalDate creationDate, AccountHolder accountHolder,
                      BigDecimal interestRate, BigDecimal creditLimit) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate, accountHolder);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
