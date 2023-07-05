package com.cac.minibank.repository;

import com.cac.minibank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(Long id);
    List<Customer> findCustomersByName(String name);
}
