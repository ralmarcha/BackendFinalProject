package com.ironhack.backendProject.models.embeddeds;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PrimaryAddress {

    private String address;
    private int postalCode;
    private String city;
    private String country;

    public PrimaryAddress(String address, int postalCode, String city, String country) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

}
