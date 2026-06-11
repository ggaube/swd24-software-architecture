package com.example.sqlservice.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initCustomers(CustomerRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Customer(1L, "Max Mustermann", "max@example.com"));
                repository.save(new Customer(2L, "Erika Musterfrau", "erika@example.com"));
                repository.save(new Customer(3L, "Hans Beispiel", "hans@example.com"));
            }
        };
    }
}
