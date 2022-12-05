package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Checking extends Account {

//TODO When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.


@Min(value = 250)
private BigDecimal minimumBalance;
private BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);

@Enumerated(EnumType.STRING)
private Status status;

  public Checking(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                  LocalDate creationDate, BigDecimal minimumBalance,
                  Status status) {
    super(secretKey, balance, primaryOwner, secondaryOwner, creationDate);
    this.minimumBalance = minimumBalance;
    this.status = status;
  }
}