package com.cac.minibank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @NotEmpty(message = "Street is required")
    private String street;
    @NotEmpty(message = "Civic number is required")
    private String civicNumber;
    private String apartment;
    private String floor;
    @NotEmpty(message = "City is required")
    private String city;
    private String zipCode;
    @NotEmpty(message = "Province is required")
    private String province;

}
