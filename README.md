# Support Ticketing

Ticket assignment, status workflow, comments and activity timeline

## Business functionality
Ticket assignment; controlled status transitions; threaded comments; priority; pageable requester ticket listing.

## Technology and engineering practices
- Java 21 and Spring Boot 3.4
- REST controllers, DTO validation and service/repository separation
- Spring Data JPA with PostgreSQL
- Transactional business operations and optimistic locking on core records
- Actuator health/metrics endpoints
- Maven build and GitHub Actions CI
- Multi-stage Docker image running as a non-root user
- Docker Compose with PostgreSQL health check
- Kubernetes Deployment/Service with probes, resources and Secret references

## Run
```bash
docker compose up --build
```
Service port: `8085`. Health: `http://localhost:8085/actuator/health`

Local run: configure PostgreSQL connection in environment, then `mvn spring-boot:run`.
Test: `mvn clean verify`.

## API outline
See controller classes for exact request contracts.
- `POST /api/v1/tickets` create ticket
- `GET /api/v1/tickets/{id}` retrieve ticket
- `PATCH /api/v1/tickets/{id}/assign?agentId=12` assign agent
- `PATCH /api/v1/tickets/{id}/status?target=IN_PROGRESS` transition
- `POST /api/v1/tickets/{id}/comments` add comment
- `GET /api/v1/tickets?requesterId=10` list requester tickets

## Suggested intermediate/advanced DevOps labs
1. Add unit and integration tests for business rules, not only application context.
2. Add JaCoCo coverage gates and publish test reports.
3. Add Flyway migrations; replace `ddl-auto: update` before production.
4. Add Testcontainers-based PostgreSQL integration tests.
5. Build immutable Docker tags using Git SHA; push to Docker Hub/ECR/GHCR.
6. Add Trivy scanning and generate an SBOM.
7. Deploy to Kubernetes; configure DB Secret and PostgreSQL service separately.
8. Add HPA, resource tuning, structured logs, metrics and distributed tracing.
9. Add OpenAPI documentation, authentication and role-based authorization.

## Training disclaimer
These are substantial learning starters, not production-ready systems. Identity IDs are simplified; authentication/authorization, payment/provider integrations, robust audit security and full integration test coverage are left as deliberate extension exercises. Review and run `mvn clean verify` before using them in class.
