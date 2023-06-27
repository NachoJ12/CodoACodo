package com.cac.minibank.model.movement;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("currency_exchange")
// Compra-venta de moneda extranjera
public class CurrencyExchange extends Movement{

    private BigDecimal exchangeRate;
    private BigDecimal comission;
}
