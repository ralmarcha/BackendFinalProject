package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class StudentChecking extends Account{
    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner,
                           LocalDate creationDate, LocalDate lastUpdateDate, Status status) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate, lastUpdateDate);
        this.status = status;
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
