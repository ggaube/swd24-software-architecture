# Demo Spring Boot Projekt: API Gateway + Zipkin

Dieses Demo enthält zwei Spring-Boot-Anwendungen:

- `gateway` auf Port `8080`
- `order-service` auf Port `8081`

Das Gateway routet Requests von `/api/orders/**` an den `order-service` weiter. 
Beide Anwendungen exportieren Traces nach Zipkin auf `http://localhost:9411`.



## Tech Stack
- Spring Boot `4.0.5`
- Spring Cloud `2025.1.0`
- Java `21`
- Docker (für Zipkin)

## Projektstruktur

```text
.
├── pom.xml
├── docker-compose.yml
├── gateway
│   ├── pom.xml
│   └── src/main
│       ├── java/com/example/gateway/GatewayApplication.java
│       └── resources/application.yml
└── order-service
    ├── pom.xml
    └── src/main
        ├── java/com/example/orderservice/OrderServiceApplication.java
        ├── java/com/example/orderservice/web/OrderController.java
        └── resources/application.yml
```


## Start Zipkin with Docker
docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin


## Tests via Gateway:
- http://localhost:8080/api/orders
- http://localhost:8080/api/orders/42

```json
{
  "id": "42",
  "status": "CREATED",
  "trackingNumber": "...",
  "timestamp": "2026-04-07T...Z"
}
```

## At Zipkin UI these Traces are available:
- http://localhost:9411

- `api-gateway`
- `order-service`

## Wichtige Punkte

- Die Sampling-Rate ist im Demo auf `1.0` gesetzt, damit jeder 
  Request in Zipkin sichtbar ist.
- Das Gateway nutzt `spring-cloud-starter-gateway-server-webflux`.
- Der Service nutzt Spring MVC über `spring-boot-starter-webmvc`.
- Die Zipkin-Export-Konfiguration läuft 
  über `management.tracing.export.zipkin.endpoint`.

## Endpoints

### Gateway
- `http://localhost:8080/actuator/health`
- `http://localhost:8080/actuator/gateway/routes`

### Order Service
- `http://localhost:8081/actuator/health`
- `http://localhost:8081/orders/42`
