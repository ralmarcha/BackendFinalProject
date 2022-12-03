package com.ironhack.backendProject.models;

import com.ironhack.backendProject.enums.CheckingType;
import com.ironhack.backendProject.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account {

//TODO When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
//TODO Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12
// The penaltyFee for all accounts should be 40.
// If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically

private BigDecimal minimumBalance;
private BigDecimal monthlyMaintenanceFee;

@Enumerated(EnumType.STRING)
private Status status;

@Enumerated(EnumType.STRING)
private CheckingType  checkingType;

    public Checking() {
    }

    public Checking(String secretKey, BigDecimal balance, String primaryOwner, String secondaryOwner,
                    LocalDate creationDate, AccountHolder accountHolder,
                    BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee, Status status,
                    CheckingType checkingType) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate,  accountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
        this.checkingType = checkingType;
    }

    public CheckingType getCheckingType() {
        return checkingType;
    }

    public void setCheckingType(CheckingType checkingType) {
        this.checkingType = checkingType;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}