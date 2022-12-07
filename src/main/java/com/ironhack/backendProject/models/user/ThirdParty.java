package com.ironhack.backendProject.models.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ThirdParty extends User{

    @NotEmpty
    private String hashKey;

    @NotEmpty
    private String name;

    public ThirdParty(String hashKey, String name) {
        this.hashKey = hashKey;
        this.name = name;
    }
}
