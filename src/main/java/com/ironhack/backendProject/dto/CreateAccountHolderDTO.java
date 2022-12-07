package com.ironhack.backendProject.dto;

import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountHolderDTO {
    private String username;
    private String password;
    @Embedded
    private PrimaryAddress address;
    private LocalDate dateOfBirth;

}
