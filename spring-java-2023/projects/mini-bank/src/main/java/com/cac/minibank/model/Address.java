package com.cac.minibank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Address {
    @Id
    @NotNull
    @Column(name = "address_id")
    private Long addressId;
    @NotEmpty
    private String street;
    @NotEmpty
    private String civicNumber;
    private String apartment;
    private String floor;
    @NotEmpty
    private String city;

    private String zipCode;
    @NotEmpty
    private String province;

}
