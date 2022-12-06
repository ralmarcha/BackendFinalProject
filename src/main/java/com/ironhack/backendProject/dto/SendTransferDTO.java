package com.ironhack.backendProject.dto;

import jakarta.validation.constraints.Digits;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SendTransferDTO {
    private Long accountId;
    @Digits(integer = 6, fraction = 2)
    private double amount;

}
