package com.example.nosqlservice.preference;

public record PreferenceResponse(Long customerId, String favoriteCategory, boolean newsletter) {
}
