package com.cac.minibank.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountRequestDTO {
    @NotNull
    private Long customerId;
    private int currencyId;
    List<Long> jointAccountHoldersId;

}
