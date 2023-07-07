package com.cac.minibank.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private Long accountId;
    private Long number;
    private LocalDate creationDate;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private BigDecimal agreedOverdraft;
    private LocalDate accountClosingDate;
    private CurrencyResponseDTO currency;
    private String accountHolderFullName;
    private List<String> jointAccountHolders;


}


