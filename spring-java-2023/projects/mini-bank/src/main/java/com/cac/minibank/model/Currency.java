package com.cac.minibank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    //@Column(name = "currency_id")
    private Integer currencyId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String code;


}
