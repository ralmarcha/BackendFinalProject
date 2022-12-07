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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentChecking extends Account{
    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking(String secretKey, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                           Status status) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        this.status = status;
    }

    }
