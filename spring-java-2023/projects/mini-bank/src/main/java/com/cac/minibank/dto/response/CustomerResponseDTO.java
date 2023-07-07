package com.cac.minibank.dto.response;

import com.cac.minibank.model.Address;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private Long customerId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Address address;
    private List<Long> accountsId;
    private List<Long> jointAccountsId;

}
