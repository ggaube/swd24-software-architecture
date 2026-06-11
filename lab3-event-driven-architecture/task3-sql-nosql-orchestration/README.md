# Spring Multi-Module: SQL + NoSQL + Orchestrator

## Modules
- `sql-service`   Port `8081`
- `nosql-service`   Port `8082`
- `orchestrator-service`   Port `8080`

## Tech Stack
- Java 21+
- Maven 3.6+
- MongoDB Compass `mongodb://localhost:27017/customerprefs`
- MongoDB Atlas with Connection String 
- Optional MongoDB with Docker 


## Start Sequence
### 1. SQL-Service
### 2. NoSQL-Service
### 3. Orchestrator-Service

## OpenAPI UI
- SQL Service: http://localhost:8081/swagger-ui/index.html
- noSQL Service: http://localhost:8082/swagger-ui/index.html
- Orchestration Service: http://localhost:8080/swagger-ui/index.html

## API Test
```bash
curl http://localhost:8080/api/customers/1/profile
```

Response:

```json
{
  "customerId": 1,
  "name": "Max Mustermann",
  "email": "max@example.com",
  "preferences": {
    "customerId": 1,
    "favoriteCategory": "books",
    "newsletter": true
  }
}
```


