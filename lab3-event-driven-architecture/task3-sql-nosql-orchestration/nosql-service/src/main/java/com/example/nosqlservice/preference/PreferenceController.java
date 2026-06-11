package com.example.nosqlservice.preference;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/preferences")
public class PreferenceController {

    private final CustomerPreferenceRepository repository;

    public PreferenceController(CustomerPreferenceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<PreferenceResponse> findByCustomerId(@PathVariable Long customerId) {
        return repository.findByCustomerId(customerId)
                .map(preference -> ResponseEntity.ok(new PreferenceResponse(
                        preference.getCustomerId(),
                        preference.getFavoriteCategory(),
                        preference.isNewsletter()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
