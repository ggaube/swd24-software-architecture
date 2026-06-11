package com.example.sqlservice.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/customers")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(customer -> ResponseEntity.ok(new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
