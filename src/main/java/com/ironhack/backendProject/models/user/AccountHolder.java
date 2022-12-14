package com.ironhack.backendProject.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountHolder extends User{

@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate dateOfBirth;
@Embedded
@AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "address")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
private PrimaryAddress primaryAddress;
private String mailingAddress;

@OneToMany(mappedBy = "primaryOwner", fetch = FetchType.EAGER)
@JsonIgnore
private List<Account> accountList = new ArrayList<>();

@OneToMany(mappedBy = "secondaryOwner", fetch = FetchType.EAGER)
@JsonIgnore
private List<Account> secondaryAccountList = new ArrayList<>();
public AccountHolder(String username, String password, LocalDate dateOfBirth,
                         PrimaryAddress primaryAddress, String mailingAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

}
