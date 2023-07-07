package com.cac.minibank.controller;

import com.cac.minibank.dto.response.CustomerResponseDTO;
import com.cac.minibank.model.Customer;
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

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomersByName(@PathVariable String name){
        return ResponseEntity.ok(customerService.getCustomersByName(name));
    }


}


