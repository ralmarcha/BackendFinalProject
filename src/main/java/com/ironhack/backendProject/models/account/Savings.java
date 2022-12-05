package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
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

  }
