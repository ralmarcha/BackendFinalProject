package com.ironhack.backendProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder {
//TODO extends User

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Long id;
@NotEmpty
private String name;
//@Past
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

@OneToMany(mappedBy = "accountHolder", fetch = FetchType.EAGER)
@JsonIgnore
private List<Account> accountList = new ArrayList<>();

    public AccountHolder() {
    }

    public AccountHolder(String name, LocalDate dateOfBirth, PrimaryAddress primaryAddress, String mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PrimaryAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(PrimaryAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
