package com.cac.minibank.service;

import com.cac.minibank.dto.response.CustomerResponseDTO;
import com.cac.minibank.model.Address;
import com.cac.minibank.model.Customer;
import com.cac.minibank.mapper.CustomerMapper;
import com.cac.minibank.repository.AddressRepository;
import com.cac.minibank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.customerMapper = customerMapper;
    }

    public void createCustomerWithAddress(Customer customer) {
        Address address = customer.getAddress();
        customer.setAddress(null); // Evitar la referencia circular en la serialización JSON

        // Guardar el objeto Address antes de asociarlo al Customer
        addressRepository.save(address);

        // Asociar el Address guardado al Customer
        customer.setAddress(address);

        // Guardar el Customer junto con el Address
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
                        .stream().map(customer -> customerMapper.toDto(customer)
                ).collect(Collectors.toList());

        return customersResponseDTO;
    }

}
