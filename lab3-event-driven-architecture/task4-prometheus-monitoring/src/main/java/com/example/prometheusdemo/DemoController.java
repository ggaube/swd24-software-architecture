package com.example.prometheusdemo;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.Random;

@RestController
public class DemoController {

    private final Counter helloCounter;
    private final Counter errorCounter;
    private final Timer businessTimer;
    private final Random random = new Random();

    public DemoController(MeterRegistry meterRegistry) {
        this.helloCounter = Counter.builder("demo_hello_requests_total")
                .description("Number of calls to /hello")
                .register(meterRegistry);

        this.errorCounter = Counter.builder("demo_error_requests_total")
                .description("Number of simulated errors")
                .register(meterRegistry);

        this.businessTimer = Timer.builder("demo_business_operation_seconds")
                .description("Duration of simulated business operation")
                .register(meterRegistry);
    }

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of(
                "message", "Spring Boot Prometheus Demo is running",
                "health", "/actuator/health",
                "metrics", "/actuator/metrics",
                "prometheus", "/actuator/prometheus",
                "hello", "/hello?name=Gerhard",
                "work", "/work",
                "fail", "/fail"
        );
    }

    @Timed(value = "demo_hello_latency_seconds", description = "Latency of /hello endpoint")
    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(defaultValue = "Student") String name) {
        helloCounter.increment();
        return Map.of(
                "message", "Hello " + name,
                "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/work")
    public Map<String, Object> work() {
        return businessTimer.record(() -> {
            int sleepMs = 100 + random.nextInt(900);
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return Map.of(
                    "status", "finished",
                    "simulatedDurationMs", sleepMs
            );
        });
    }

    @GetMapping("/fail")
    public Map<String, String> fail() {
        errorCounter.increment();
        throw new RuntimeException("Simulated application error for monitoring demo");
    }
}
