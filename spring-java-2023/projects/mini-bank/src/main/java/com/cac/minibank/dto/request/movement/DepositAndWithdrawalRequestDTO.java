package com.cac.minibank.dto.request.movement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DepositAndWithdrawalRequestDTO {
    private Long accountId;
    private BigDecimal amount;
    private String description;
    private String cashier;

}
