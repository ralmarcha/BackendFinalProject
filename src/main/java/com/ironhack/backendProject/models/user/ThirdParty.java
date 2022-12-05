package com.ironhack.backendProject.models.user;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ThirdParty extends User{

    @NotEmpty
    private String hashedKey;

    public ThirdParty(String username, String password) {
        super(username, password);
    }
}
