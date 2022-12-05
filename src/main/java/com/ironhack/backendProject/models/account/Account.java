package com.ironhack.backendProject.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
import com.ironhack.backendProject.models.Transaction;
import com.ironhack.backendProject.models.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Account {

    // TODO The penaltyFee for all accounts should be 40.
// If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically
    //TODO apply penaltyFee, lastUpdateDate(), updateBalance()
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long Id;
@NotEmpty
private String secretKey;

@Digits(integer=9, fraction= 2)
private BigDecimal balance;

@ManyToOne
@JoinColumn
private User primaryOwner;

private String secondaryOwner;

private final BigDecimal PENALTY_FEE = new BigDecimal(40);

@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate creationDate;
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate lastUpdateDate;


@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
@JsonIgnore
private List<Transaction> transactionLists = new ArrayList<>();


/*   @JsonIgnore
    @OneToMany(mappedBy = "originAccount")
    List<Transfer> transfersSent;

    @JsonIgnore
    @OneToMany(mappedBy = "destinationAccount")
    List<Transfer> transfersReceived;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Interest> interest;*/

    public Account(String secretKey, BigDecimal balance, User primaryOwner, String secondaryOwner, LocalDate creationDate) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
    }
}