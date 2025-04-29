# DTB Microservices Banking Platform

This project is a microservices-based banking platform built with Spring Boot. It supports user profile management, account operations, and payment transactions. The system is designed with scalability, modularity, and security in mind.

## 🧩 Architecture Overview

The system is composed of the following microservices:

- **Profile Service**: Handles user registration, login, and JWT-based authentication.
- **Store of Value (Account) Service**: Manages account creation, balance updates, and account data.
- **Payment Service**: Facilitates money transfers, deposits, withdrawals, and transaction history.
- **Notification/Event Service**: Publishes events and sends notifications on significant actions.

Each service exposes REST APIs and communicates internally via HTTP (could be upgraded to async messaging with RabbitMQ/Kafka).

### 📊 Architecture Diagram


## 🔐 Security Design

- **Authentication**: JWT tokens are issued by the Profile Service upon login.
- **Authorization**: Secured endpoints require valid JWT tokens.
- **Data Transmission**: All inter-service and client communications are assumed over HTTPS.
- **Sensitive Data**: Passwords are stored hashed using BCrypt.

## 🔁 Data Consistency

We follow the **eventual consistency** model:
- Use **local transactions** in each service.
- Publish events (e.g., after a transaction or balance update) to notify other services asynchronously.
- Services consume and react to these events (e.g., Notification Service listens for `TransactionCompletedEvent`).

For critical flows like balance deduction + transaction recording:
- Implement the **SAGA pattern** or **two-phase commit** if stronger consistency is needed.

## ⚙️ Scalability & High Availability

- **Stateless services**: All services are stateless and can be horizontally scaled.
- **Database**: Use managed relational DBs with replication and failover.
- **Containerization**: Services are containerized using Docker.
- **Orchestration**: Can be deployed on Kubernetes (K8s) for scaling and self-healing.

## 🌪 Disaster Recovery

- **Backups**: Regular automated DB backups.
- **Observability**: Logging, health checks, and alerts using tools like Prometheus/Grafana.
- **CI/CD**: GitHub Actions or Jenkins to enable fast, safe deployments with rollback support.

---

## 🐳 Running the Project with Docker Compose

```bash
docker-compose up --build


