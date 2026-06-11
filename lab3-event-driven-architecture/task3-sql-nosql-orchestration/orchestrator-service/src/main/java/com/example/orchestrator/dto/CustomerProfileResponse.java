package com.example.orchestrator.dto;

public record CustomerProfileResponse(Long customerId,
                                      String name,
                                      String email,
                                      PreferenceDto preferences) {
}
