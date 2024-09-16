# Ticket to Ride
- An API for money transferring between accounts in different currencies with frequent currency updates.
## Features
- Get latest currency rates from external API.
- Users can login and create accounts.
- - Only authenticated Users can use API.
- Account can be one of multiple currencies.
- - Different currencies are automatically converted.
- - User can top-up their balances.
- Account can have limits for expenses.
- - Limit can be set by User.
- - User can't make transactions after he reaches that limit.
- Users can make transactions to other accounts (expenses / income).
- - Can get list of transactions by different statuses:
- - SUCCESS / NOT_ENOUGH_BALANCE / LIMIT_EXCEEDED.

## Technologies Used
- Java 21
- Spring Boot
- PostgreSQL
- Gradle
- JUnit 5
- Mockito
- Liquibase

## Installation

1. **Docker compose:**
    ```bash
    docker-compose up
    ```
- default port: 8080
- docker port: 8000
## API Endpoints

**Postman API documentation:**
https://documenter.getpostman.com/view/38201869/2sAXqp8PTs
