package com.ironhack.backendProject.dto.users;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.backendProject.LocalDateDeserializer;
import com.ironhack.backendProject.LocalDateSerializer;
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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

}
