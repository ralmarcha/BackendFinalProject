package com.ironhack.backendProject.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
import com.ironhack.backendProject.models.account.Account;
import jakarta.persistence.*;
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
public class Transaction {

    //TODO checkFunds()
    //TODO updateBalance()
    //TODO si hay tiempo hacer deposit y withdraw con el enum
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfTransaction;

    @Digits(integer=9, fraction= 2)
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(LocalDate dateOfTransaction, BigDecimal transactionAmount) {
        this.dateOfTransaction = dateOfTransaction;
        this.transactionAmount = transactionAmount;
    }

   }
