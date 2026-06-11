package com.example.orchestrator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    @Bean
    public feign.Request.Options feignRequestOptions() {
        return new feign.Request.Options(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS, true);
    }
}
