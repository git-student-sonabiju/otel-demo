# OpenTelemetry Demo

A Spring Boot application demonstrating OpenTelemetry integration for observability.

## Overview

This project showcases how to integrate OpenTelemetry with a Spring Boot application to collect traces, metrics, and logs. It includes:

- A simple REST API with two endpoints
- OpenTelemetry tracing and metrics configuration
- Docker Compose setup for local development with Jaeger and OpenTelemetry Collector
- Maven-based build system

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Building the Application](#building-the-application)
- [Running Tests](#running-tests)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Key Technologies](#key-technologies)
- [Observability Features](#observability-features)
- [Configuration](#configuration)
- [Common Development Tasks](#common-development-tasks)

## Getting Started

### Prerequisites

- Java 21
- Docker and Docker Compose
- Maven (wrapper provided via `mvnw`)

### Building the Application

```bash
# Build the project
./mvnw clean package

# Skip tests during build (faster iteration)
./mvnw clean package -DskipTests
```

### Running Tests

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=OtelDemoApplicationTests

# Run a specific test method (if using JUnit 5)
./mvnw test -Dtest=OtelDemoApplicationTests#contextLoads
```

### Running the Application

```bash
# Run the Spring Boot application
./mvnw spring-boot:run

# Run with Docker Compose (includes Jaeger and OTel collector)
docker-compose up

# Run just the application with Docker Compose services
docker-compose up jaeger otel-collector
./mvnw spring-boot:run
```

## API Endpoints

Once running, the application is available at `http://localhost:8080`:

- `GET /` - Returns "Hello World"
- `POST /users` - Creates a user (expects JSON body with "name" field)

Example usage:
```bash
# Get greeting
curl http://localhost:8080/

# Create user
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe"}'
```

## Project Structure

### Source Code
- `src/main/java/com/example/otel_demo/OtelDemoApplication.java` - Main Spring Boot application class
- `src/main/java/com/example/otel_demo/controller/SimpleController.java` - REST controller with API endpoints

### Configuration
- `pom.xml` - Maven dependencies including Spring Boot, OpenTelemetry, and Actuator
- `otel-collector-config.yaml` - OpenTelemetry collector configuration
- `docker-compose.yml` - Services for Jaeger tracing and OpenTelemetry collector

### Testing
- `src/test/java/com/example/otel_demo/OtelDemoApplicationTests.java` - Basic application test

## Key Technologies

- **Java 21** - Language version
- **Spring Boot 3.4.1** - Application framework
- **OpenTelemetry** - Observability (tracing and metrics)
- **Micrometer** - Metrics collection and export
- **Jaeger** - Distributed tracing backend (via Docker Compose)
- **Maven** - Build and dependency management

## Observability Features

The application is configured to export:

### Traces
- Via OpenTelemetry to Jaeger
- Access Jaeger UI at: http://localhost:16686
- View traces for incoming requests and outgoing calls

### Metrics
- Via Prometheus format
- Access metrics at: http://localhost:8080/actuator/prometheus
- Includes JVM metrics, application metrics, and custom business metrics

### Logs
- Structured logging with trace context
- Trace IDs are included in log messages for correlation

## Configuration

### Maven Dependencies (`pom.xml`)
Includes:
- Spring Boot 3.4.1
- OpenTelemetry SDK
- Micrometer for metrics
- Spring Boot Actuator
- Logging dependencies

### OpenTelemetry Collector (`otel-collector-config.yaml`)
Configuration for the OpenTelemetry collector that:
- Receives traces and metrics
- Exports to Jaeger (for tracing)
- Exposes Prometheus endpoint (for metrics)

### Docker Compose (`docker-compose.yml`)
Defines services for:
- Jaeger: Distributed tracing UI and backend
- OpenTelemetry Collector: Agent that collects and exports telemetry data

## Common Development Tasks

1. **Adding new endpoints**: Add methods to `SimpleController.java` or create new controller classes
2. **Configuring OpenTelemetry**: Modify `otel-collector-config.yaml` for different exporters or sampling rates
3. **Updating dependencies**: Edit `pom.xml` to add or update libraries
4. **Running tests**: Use the Maven commands above to validate changes

## Viewing Observability Data

After running the application with Docker Compose:
1. **Jaeger UI** (traces): http://localhost:16686
2. **Prometheus metrics**: http://localhost:8080/actuator/prometheus

## License

This project is open source and available under the [MIT License](LICENSE).
