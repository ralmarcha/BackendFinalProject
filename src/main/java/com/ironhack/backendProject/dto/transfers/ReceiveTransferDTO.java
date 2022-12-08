package com.ironhack.backendProject.dto.transfers;

import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReceiveTransferDTO {

    private Long accountId;

    private String secretKey;

    @Digits(integer = 9, fraction = 2)
    private BigDecimal amount;
}
