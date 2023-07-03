package com.cac.minibank.service;

import com.cac.minibank.model.Address;
import com.cac.minibank.model.Customer;
import com.cac.minibank.repository.AddressRepository;
import com.cac.minibank.repository.CustomerRepository;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
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

}
