package com.ironhack.backendProject.models.account;

import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
                           LocalDate creationDate, Status status) {
        super(secretKey, balance, primaryOwner, secondaryOwner, creationDate);
        this.status = status;
    }
}
