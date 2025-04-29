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

