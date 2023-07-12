package com.cac.minibank.dto.request.movement;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DepositAndWithdrawalRequestDTO {
    @NotNull(message = "AccountId is required")
    private Long accountId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotEmpty(message = "Cashier is required")
    private String cashier;

}
