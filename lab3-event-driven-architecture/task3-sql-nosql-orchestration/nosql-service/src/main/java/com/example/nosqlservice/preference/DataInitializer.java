package com.example.nosqlservice.preference;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initPreferences(CustomerPreferenceRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new CustomerPreference(null, 1L, "books", true));
                repository.save(new CustomerPreference(null, 2L, "electronics", false));
            }
        };
    }
}
