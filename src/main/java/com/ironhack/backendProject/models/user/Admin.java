package com.ironhack.backendProject.models.user;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User{
    public Admin(String username, String password) {
        super(username, password);
    }
}
