package com.ironhack.backendProject.dto.accountDTO;

import com.ironhack.backendProject.dto.accountDTO.CreateAccountDTO;
import com.ironhack.backendProject.enums.Status;
import com.ironhack.backendProject.models.user.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCheckingAccountDTO extends CreateAccountDTO {
    private Status status;

 }
