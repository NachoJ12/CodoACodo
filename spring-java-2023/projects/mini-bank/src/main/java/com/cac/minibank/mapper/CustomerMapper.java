package com.cac.minibank.mapper;

import com.cac.minibank.dto.response.CustomerResponseDTO;
import com.cac.minibank.model.Account;
import com.cac.minibank.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerResponseDTO toDto(Customer customer){
        if (customer == null) {
            return null;
        }

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setCustomerId(customer.getCustomerId());
        customerResponseDTO.setName(customer.getName());
        customerResponseDTO.setSurname(customer.getSurname());
        customerResponseDTO.setPhone(customer.getPhone());
        customerResponseDTO.setEmail(customer.getEmail());
        customerResponseDTO.setAddress(customer.getAddress());

        List<Long> accountsId = customer.getAccounts()
                .stream()
                .map(Account::getAccountId)
                .collect(Collectors.toList());

        List<Long> jointAccountsId = customer.getJointAccounts()
                .stream()
                .map(Account::getAccountId)
                .collect(Collectors.toList());

        customerResponseDTO.setAccountsId(accountsId);
        customerResponseDTO.setJointAccountsId(jointAccountsId);

        return customerResponseDTO;

    }

}
