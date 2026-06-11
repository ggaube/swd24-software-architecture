package com.example.orchestrator.controller;

import com.example.orchestrator.dto.CustomerProfileResponse;
import com.example.orchestrator.service.CustomerProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerProfileController {

    private final CustomerProfileService service;

    public CustomerProfileController(CustomerProfileService service) {
        this.service = service;
    }

    @GetMapping("/{id}/profile")
    public CustomerProfileResponse getProfile(@PathVariable Long id) {
        return service.getProfile(id);
    }
}
