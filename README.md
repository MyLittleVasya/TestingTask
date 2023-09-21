## TestingTask
# Testing task for Teamvoy

This project is a simple Spring Boot application demonstrating a web service for product and orders.

## Prerequisites

- Java 11
- Maven
- Postman

## Getting Started

### Cloning the repository:

```
bash
git clone https://github.com/your-username/my-spring-boot-web-project.git
```

### Building and testing the project:

If you have Maven installed:

```
bash
mvn clean install
```

Or you can use the Maven Wrapper:

bash
```./mvnw clean install```

### Running the application:

Using Maven:
```
bash
mvn spring-boot:run
```

Or using the built JAR:

```bash
java -jar target/my-spring-boot-web-project-0.0.1-SNAPSHOT.jar
```

The application will start and by default, you can access it at http://localhost:8080.

# API Endpoints

## Orders

#### Create a new order

- **Method:** POST
- **Endpoint:** `/orders/create`
- **Description:** Create a new order.

#### Get order details

- **Method:** GET
- **Endpoint:** `/orders/{orderId}`
- **Description:** Get order details by order ID.

#### Delete an order

- **Method:** DELETE
- **Endpoint:** `/orders/{orderId}`
- **Description:** Delete an order by order ID.

#### Get a list of orders with pagination

- **Method:** GET
- **Endpoint:** `/orders/page/{pageNumber}`
- **Description:** Get a list of orders with pagination.

#### Pay for an order

- **Method:** POST
- **Endpoint:** `/orders/pay/{orderId}`
- **Description:** Pay for an order with a given order ID.

## Product

#### Create a new product

- **Method:** POST
- **Endpoint:** `/products/create`
- **Description:** Create a new product.

#### Get product details

- **Method:** GET
- **Endpoint:** `/products/{productId}`
- **Description:** Get product details by product ID.

#### Get a list of products with pagination

- **Method:** GET
- **Endpoint:** `/products/page/{pageNumber}`
- **Description:** Get a list of products with pagination.

#### Delete a product

- **Method:** DELETE
- **Endpoint:** `/products/{productId}`
- **Description:** Delete a product by product ID.

## General
#### Welcome

- **Method:** POST
- **Endpoint:** `/welcome`
- **Description:** Returns a simple welcome message.
