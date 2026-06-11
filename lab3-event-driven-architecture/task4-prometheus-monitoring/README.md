# Spring Boot Prometheus Demo

This demo exposes Spring Boot Actuator metrics in Prometheus format.


Metrics Endpoints:

```text
http://localhost:8080/
http://localhost:8080/actuator/health
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/prometheus
```

## Generate some metrics
Call these endpoints several times:

```bash
curl "http://localhost:8080/hello?name=Gerhard"
curl "http://localhost:8080/work"
curl "http://localhost:8080/fail"
```

The `/fail` endpoint intentionally returns HTTP 500 to create error metrics.


Open Prometheus:
```text
http://localhost:9090
```



## Important configuration

`pom.xml` contains:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

`application.yml` exposes the Prometheus endpoint:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
```
