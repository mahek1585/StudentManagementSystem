
# Student Management System

A Java-based Student Management System built using Core Java, JDBC, MySQL, and Maven.

## Features

- Add new students
- View all students
- Search students by ID and name
- Update student details
- Delete student records
- Sort students by name, marks, and ID
- Input validation and exception handling
- Database integration using JDBC

## Technologies Used

- Java
- MySQL
- JDBC
- Maven
- JUnit 5

## Project Architecture

- Main: Application entry point
- Model: Student and Person classes
- DAO: Database operations
- Service: Business logic
- Util: Database connection and helper classes

## Database

Database Name: student_management_system

Database: MySQL

Credentials are configured using Windows Environment Variables.

## Run the Project

Run tests:

```bash
mvn clean test
```

Run the application:

```bash
mvn exec:java "-Dexec.mainClass=Main"
```

## Testing

Automated tests are written using JUnit 5.

Latest test result: 19 tests passed successfully.
