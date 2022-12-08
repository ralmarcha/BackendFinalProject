package com.ironhack.backendProject.dto.accounts;

import com.ironhack.backendProject.models.user.AccountHolder;

import java.time.LocalDate;

public class UpdateAccountDTO {

    private String secretKey;
    private LocalDate lastUpdateDate;
    private Long primaryOwnerId;
    private LocalDate creationDate;
    private AccountHolder secondaryOwner;
}
