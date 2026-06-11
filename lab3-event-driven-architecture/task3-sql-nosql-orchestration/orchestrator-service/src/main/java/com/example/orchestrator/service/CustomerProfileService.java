package com.example.orchestrator.service;

import com.example.orchestrator.client.NoSqlServiceClient;
import com.example.orchestrator.client.SqlServiceClient;
import com.example.orchestrator.dto.CustomerDto;
import com.example.orchestrator.dto.CustomerProfileResponse;
import com.example.orchestrator.dto.PreferenceDto;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService {

    private final SqlServiceClient sqlServiceClient;
    private final NoSqlServiceClient noSqlServiceClient;

    public CustomerProfileService(SqlServiceClient sqlServiceClient,
                                  NoSqlServiceClient noSqlServiceClient) {
        this.sqlServiceClient = sqlServiceClient;
        this.noSqlServiceClient = noSqlServiceClient;
    }

    public CustomerProfileResponse getProfile(Long customerId) {
        CustomerDto customer = sqlServiceClient.findCustomerById(customerId);
        PreferenceDto preferences = fetchPreferencesOrNull(customerId);

        return new CustomerProfileResponse(
                customer.id(),
                customer.name(),
                customer.email(),
                preferences
        );
    }

    private PreferenceDto fetchPreferencesOrNull(Long customerId) {
        try {
            return noSqlServiceClient.findPreferenceByCustomerId(customerId);
        } catch (FeignException.NotFound ex) {
            return null;
        }
    }
}
