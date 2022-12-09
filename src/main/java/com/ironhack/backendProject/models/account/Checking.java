package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checking extends Account {

private BigDecimal minimumBalance = new BigDecimal("250").setScale(2, RoundingMode.HALF_EVEN);
private BigDecimal monthlyMaintenanceFee = new BigDecimal("12").setScale(2, RoundingMode.HALF_EVEN);
@Enumerated(EnumType.STRING)
private Status status;

  public Checking(String secretKey, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                  Status status) {
    super(secretKey, balance, primaryOwner, secondaryOwner);
    this.status = status;
  }
}
