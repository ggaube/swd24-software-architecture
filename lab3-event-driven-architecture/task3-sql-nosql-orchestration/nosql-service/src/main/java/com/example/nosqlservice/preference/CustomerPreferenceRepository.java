package com.example.nosqlservice.preference;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerPreferenceRepository extends MongoRepository<CustomerPreference, String> {
    Optional<CustomerPreference> findByCustomerId(Long customerId);
}
