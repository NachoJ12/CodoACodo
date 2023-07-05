package com.cac.minibank.controller;

import com.cac.minibank.model.Address;
import com.cac.minibank.model.Customer;
import com.cac.minibank.repository.AddressRepository;
import com.cac.minibank.repository.CustomerRepository;
import com.cac.minibank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name){
        return ResponseEntity.ok(customerService.getCustomersByName(name));
    }


}


