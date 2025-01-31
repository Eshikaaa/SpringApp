## Overview
This Java-based Spring Boot application provides API endpoints for managing product-related operations. It is containerized using Docker for easy deployment and scalability.

## Features
- Implements CRUD operations for product management.
- Uses PostgreSQL as the database.
- Logging is integrated for tracking application activities.
- Exposes REST APIs for interaction with the system.
- Dockerized for containerized deployment.

## Logging
The application uses **SLF4J and Logback** for structured log management. Logging configuration:

| Property | Value |
|----------|-------|
| `logging.level.root` | TRACE |
| `logging.file.name` | app.log |

Imports used:
```java
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
```

## Docker Images & Their Roles
| Image | Role |
|-------|------|
| Application Image | Runs the Spring Boot application. |
| Database Image | Runs PostgreSQL to store application data. |


## APIs Exposed
### 1. **Product APIs**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get product by ID |
| `POST` | `/api/products` | Add a new product |
| `PUT` | `/api/products/{id}` | Update product details |
| `DELETE` | `/api/products/{id}` | Delete a product |

### Request Body for `POST /api/products`
| Field | Type | Description |
|--------|------|-------------|
| `name` | String | Name of the product |
| `price` | Double | Price of the product |

## Running the Application

### Running Without Docker
1. Install PostgreSQL and ensure it is running.
2. Configure `application.properties` with correct database credentials.
3. Build the project:
   ```sh
   mvn clean package
   ```
4. Run the application:
   ```sh
   java -jar target/app.jar
   ```

---

### Running with Docker Compose
This application is Dockerized using Docker Compose, making it easier to run the Spring Boot application and the PostgreSQL database together in containers.

Ensure that Docker and Docker Compose are installed.
Clone the repository and navigate to the project directory.
Use the following command to start the application with Docker Compose:
sh
Copy
Edit
docker-compose up --build
The application will be accessible at http://localhost:8080.
The PostgreSQL database will be available at localhost:5432.
Docker Compose Configuration
The Docker Compose configuration includes the following services:

### Application:
The Spring Boot application runs in a container.
Database: PostgreSQL runs in a separate container for storing product data.
The docker-compose.yaml file defines the following:

The Spring Boot application container connects to the PostgreSQL container via environment variables.
The PostgreSQL container stores data in a mapped volume, ensuring data persists across container restarts.
### Running on Kubernetes with Minikube

1. **Start Minikube**:
   ```sh
   minikube start

## Deploy PostgreSQL and the Spring Boot application using the respective YAML configuration files (postgres-deployment.yaml, spring-deployment.yaml).

To access the application externally, use the NodePort service configured in Kubernetes:

kubectl apply -f postgres-deployment.yaml
kubectl apply -f spring-deployment.yaml

minikube service spring-app-service --url


