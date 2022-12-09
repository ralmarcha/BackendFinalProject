package com.ironhack.backendProject.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
import com.ironhack.backendProject.models.user.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long Id;
@NotEmpty
private String secretKey;
@Digits(integer=9, fraction= 2)
private BigDecimal balance;
@ManyToOne
@JoinColumn
private AccountHolder primaryOwner;
@ManyToOne
@JoinColumn(name = "secondary_owner_id")
private AccountHolder secondaryOwner;

private final BigDecimal PENALTY_FEE = BigDecimal.valueOf(40);

@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate creationDate = LocalDate.now();

@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate lastUpdateDate= LocalDate.now();

@JsonIgnore
@OneToMany(mappedBy = "originAccount", fetch = FetchType.EAGER)
List<Transaction> transfersSent;

@JsonIgnore
@OneToMany(mappedBy = "destinationAccount", fetch = FetchType.EAGER)
List<Transaction> transfersReceived;
public Account(String secretKey, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
}
}