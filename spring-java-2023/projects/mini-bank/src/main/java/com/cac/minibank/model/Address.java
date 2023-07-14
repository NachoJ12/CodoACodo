package com.cac.minibank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
