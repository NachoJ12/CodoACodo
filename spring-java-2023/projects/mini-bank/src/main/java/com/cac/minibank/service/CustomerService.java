package com.cac.minibank.service;

import com.cac.minibank.dto.request.CustomerRequestDTO;
import com.cac.minibank.dto.response.CustomerResponseDTO;
import com.cac.minibank.model.Customer;
import com.cac.minibank.mapper.CustomerMapper;
import com.cac.minibank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public void createCustomerWithAddress(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        customerRepository.save(customer);
    }

    public CustomerResponseDTO getCustomerById(Long id){
        Customer customer = customerRepository.findByCustomerId(id);

        CustomerResponseDTO customerResponseDTO = customerMapper.toDto(customer);

        return customerResponseDTO;
    }
    public List<CustomerResponseDTO> getCustomersByName(String name){
        List<Customer> customers = customerRepository.findCustomersByName(name);

        List<CustomerResponseDTO> customersResponseDTO = customers
                        .stream().map(customerMapper::toDto
                ).collect(Collectors.toList());

        return customersResponseDTO;
    }

}
