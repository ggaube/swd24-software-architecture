package com.example.orchestrator.client;

import com.example.orchestrator.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sql-service", url = "${clients.sql-service.url}")
public interface SqlServiceClient {

    @GetMapping("/internal/customers/{id}")
    CustomerDto findCustomerById(@PathVariable("id") Long id);
}
