package com.example.orderservice.web;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final ObservationRegistry observationRegistry;

    public OrderController(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    @GetMapping("/orders/{id}")
    public Map<String, Object> getOrder(@PathVariable String id) {
        return Observation.createNotStarted("load-order", observationRegistry)
                .lowCardinalityKeyValue("order.id", id)
                .observe(() -> {
                    log.info("Loading order {}", id);

                    try {
                        Thread.sleep(120L);
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    return Map.of(
                            "id", id,
                            "status", "CREATED",
                            "trackingNumber", UUID.randomUUID().toString(),
                            "timestamp", Instant.now().toString()
                    );
                });
    }

    @GetMapping("/orders")
    public Map<String, Object> listOrders() {
        return Map.of(
                "count", 1,
                "items", java.util.List.of(Map.of("id", "42", "status", "CREATED"))
        );
    }
}
