package com.cac.minibank.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponseDTO {

    private Long movementId;
    private LocalDateTime realizationDateTime;
    private BigDecimal amount;
    private String description;
    private Long account;
    private String cashier;
    private Long destinationAccount;
    private Long sourceAccount;
    private BigDecimal exchangeRate;
    private BigDecimal comission;

}
