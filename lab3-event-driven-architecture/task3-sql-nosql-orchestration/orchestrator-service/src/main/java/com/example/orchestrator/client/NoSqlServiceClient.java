package com.example.orchestrator.client;

import com.example.orchestrator.dto.PreferenceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nosql-service", url = "${clients.nosql-service.url}")
public interface NoSqlServiceClient {

    @GetMapping("/internal/preferences/{customerId}")
    PreferenceDto findPreferenceByCustomerId(@PathVariable("customerId") Long customerId);
}
