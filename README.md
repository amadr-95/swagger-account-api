# Spring Boot REST CRUD API with Swagger Documentation

This example demonstrates how to build a Spring Boot REST CRUD API using Maven, leveraging Spring Data JPA/Hibernate to
interact with a PostgreSQL database. Additionally, the API endpoints are documented using Swagger 3 annotations.

## Overview

We'll be developing a REST CRUD API for an Account management application. Each Account in the system consists of the
following attributes: _userId, name, username, password, email, and createdOn date_

The API provides the following functionalities:

- CRUD operations for managing accounts.
- Custom finder methods such as searching by name or email domain

## API Endpoints

The API exposes the following endpoints

| Method | URL                               | Description                                              |
|--------|-----------------------------------|----------------------------------------------------------|
| GET    | /api/accounts[/:name]             | Retrieve all accounts, optionally filtered by name.      |
| GET    | /api/accounts/filter/:id          | Retrieve an account by ID.                               |
| GET    | /api/accounts/filter/:username    | Retrieve an account by username.                         |
| GET    | /api/accounts/filter/:email       | Retrieve an account by email.                            |
| GET    | /api/accounts/filter/:domain      | Retrieve accounts matching the provided email domain.    |
| POST   | /api/accounts                     | Add a new account.                                       |
| PUT    | /api/accounts/update/username/:id | Update the username of an account with the specified ID. |
| PUT    | /api/accounts/update/email/:id    | Update the email of an account with the specified ID.    |
| DELETE | /api/accounts/delete/:id          | Delete an account by ID.                                 |
| DELETE | /api/accounts/delete              | Delete all accounts.                                     |

## Swagger UI

You can explore and interact with these endpoints using Swagger UI, accessible
at: http://localhost:8080/accounts-management

![swagger](https://github.com/amadr-95/spring-jpa-postgres-swagger/assets/122611230/92bd5c25-0511-4751-bed8-5269f6d44e31)



