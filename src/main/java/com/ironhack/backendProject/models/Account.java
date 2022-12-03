package com.ironhack.backendProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long Id;
@NotEmpty
private String secretKey;
@NotEmpty
@Digits(integer=9, fraction= 2)
private BigDecimal balance;
//private BigDecimal finalBalance;
@NotEmpty
private String primaryOwner;
private String secondaryOwner;
private final BigDecimal PENALTY_FEE = new BigDecimal(40);
@NotEmpty
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate creationDate;
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate lastUpdateDate;

@ManyToOne@JoinColumn(name = "account_holder_id")
private AccountHolder accountHolder;

@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
@JsonIgnore
private List<Transfer> transferLists = new ArrayList<>();

   public Account(String secretKey, BigDecimal balance, String primaryOwner, String secondaryOwner, LocalDate creationDate) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
    }

    public Account() {
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

     public List<Transfer> getTransferLists() {
     return transferLists;
     }

    public void setTransferLists(List<Transfer> transferLists) {
    this.transferLists = transferLists;}


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

     public String getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(String primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public String getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(String secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPENALTY_FEE() {
        return PENALTY_FEE;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

   }
