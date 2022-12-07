package com.ironhack.backendProject.dto;

import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SendTransferDTO {
    private Long accountId;
    @Digits(integer = 9, fraction = 2)
    private BigDecimal amount;

}
