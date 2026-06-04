# Notes Management System 📝

A robust and scalable Notes Management System built using **Spring Boot** and **Java 17**. 

This project demonstrates the implementation of fundamental and intermediate backend development concepts, including CRUD operations, Pagination, Sorting, Logging, Exception Handling, Custom Builder Pattern, and JPA Auditing.

## 🚀 Features

* **CRUD Operations:** Create, Read, Update, and Delete notes.
* **Pagination & Sorting:** Efficiently fetch a large number of notes with page offset, page size, and custom field sorting.
* **Search Functionality:** Search for notes containing specific keywords using custom JPA query methods.
* **Input Validation:** Strict validation rules for title and content (e.g., character limits, not-blank checks).
* **JPA Auditing:** Automatically records `createdAt` and `updatedAt` timestamps using `@EnableJpaAuditing` and `AuditingEntityListener`.
* **Global Exception Handling:** Custom implementation of `@ControllerAdvice` to gracefully handle exceptions like `ResourceNotFoundException`.
* **Builder Pattern:** Custom builder pattern implementation (without Lombok) for clean and readable object instantiation.
* **Logging:** Integrated `SLF4J/Logback` logging for tracking application flow and debugging errors across the service layer.

## 🛠️ Tech Stack

* **Java Version:** 17
* **Framework:** Spring Boot 3.x
* **Build Tool:** Maven
* **Database:** MySQL
* **Dependencies:** * Spring Web
    * Spring Data JPA
    * MySQL Driver
    * Spring Boot Validation

## ⚙️ Prerequisites

Before you begin, ensure you have met the following requirements:
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) installed.
* [Maven](https://maven.apache.org/) installed.
* [MySQL Server](https://dev.mysql.com/downloads/mysql/) installed and running.
* An API testing tool like [Postman](https://www.postman.com/) or cURL.

## 🚦 Getting Started

**Clone the repository:** git clone <repository-url>

**Navigate to the project directory:** cd notes-management-system

**Build the project:** mvn clean install

**Run the application**:mvn spring-boot:run

### 🔌 API EndpointsBase URL: http://localhost:8082/api/notes

| HTTP Method | Endpoint | Description | Query Parameters |
| :--- | :--- | :--- | :--- |
| `POST` | `/` | Create a new note | None |
| `GET` | `/{id}` | Get a note by its ID | None |
| `PUT` | `/{id}` | Update an existing note | None |
| `DELETE` | `/{id}` | Delete a note by its ID | None |
| `GET` | `/` | Get all notes (Paginated) | `page` (default: 0), `size` (default: 5), `sortBy` (default: id) |
| `GET` | `/search` | Search notes by keyword | `keyword` (required) |
### Example Request Body (Create/Update Note)
```
JSON{
    "title": "Java Learning",
    "content": "Inheritance and OOP concepts",
    "category": "Programming Language",
    "pin": true
}
```
### Validation Constraints

Title: Cannot be blank. Maximum of 200 characters.

Content: Cannot be blank. Must be between 5 and 2000 characters.
## 🗄️ Database Configuration

Update the `src/main/resources/application.properties` file with your MySQL database credentials. The database will be created automatically if it doesn't exist.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/management_system?createDatabaseIfNotExist=true
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8082
