package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Checking extends Account {

private BigDecimal minimumBalance = BigDecimal.valueOf(250);
private BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);


@Enumerated(EnumType.STRING)
private Status status;

  public Checking(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                                  Status status) {
    super(secretKey, balance, primaryOwner, secondaryOwner);
    this.status = status;
  }

  @Override
  public void setBalance(BigDecimal balance) {
     // addMaintenance();
    //-----------PENALTY FEE-------------------//
    if(balance.compareTo(BigDecimal.valueOf(250))<0){
      super.setBalance(balance.subtract(super.getPENALTY_FEE())) ;
    }else{
      super.setBalance(balance);
  }
     }


    }
