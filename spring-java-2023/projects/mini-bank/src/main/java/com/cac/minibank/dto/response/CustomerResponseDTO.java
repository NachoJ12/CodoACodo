package com.cac.minibank.dto.response;

import com.cac.minibank.dto.AddressDTO;
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
    private AddressDTO addressDTO;
    private List<Long> accountsId;
    private List<Long> jointAccountsId;

}
