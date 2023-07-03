package com.cac.minibank.controller;

import com.cac.minibank.model.Address;
import com.cac.minibank.model.Customer;
import com.cac.minibank.repository.AddressRepository;
import com.cac.minibank.repository.CustomerRepository;
import com.cac.minibank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomerWithAddress(@RequestBody Customer customer) {
        customerService.createCustomerWithAddress(customer);

        return ResponseEntity.ok("Customer created with Address");
    }


}


