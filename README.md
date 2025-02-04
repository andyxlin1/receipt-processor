# Receipt Processor

A Spring Boot-based receipt processing API that calculates reward points based on receipt details.

## Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine

## Running the Application with Docker

### 1. Build the Docker Image

Navigate to the project root directory and run:

```sh
docker build -t receipt-processor .
```

### 2. Run the Container

```sh
docker run -p 8080:8080 receipt-processor
```

The application will be accessible at `http://localhost:8080`.
