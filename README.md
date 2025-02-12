# TestInditex

## Description
This project is a test implementation for Inditex. It demonstrates how to handle pricing logic for products based on specific rules.

## Requirements
- Java 17+
- Maven 3.8+

## Features
- REST API for pricing management.
- Integration with a database.
- Unit and integration tests.

## API Endpoints
GET /api/prices?date={date}&productId={id}&brandId={id}: Get pricing information.

## How to run
```bash
mvn spring-boot:run