
# Database Setup - Student Management System

## 1. Database Details

- Database Name: student_management_system
- Host: localhost
- Port: 3306
- Database User: sms_app

## 2. Environment Variables

Our application uses Windows Environment Variables to store database credentials.

- DB_USERNAME = sms_app
- DB_PASSWORD = SmsApp@2026#

## 3. DB Connection File

File Location:

src/main/java/util/DBConnection.java

## 4. Database Connection URL

jdbc:mysql://localhost:3306/student_management_system

## 5. Technologies Used

- Java
- JDBC
- MySQL
- Maven
- JUnit 5

## 6. Important Notes

- Never hardcode database passwords in Java files.
- Never upload passwords to GitHub.
- If environment variables are missing, database connection will fail.
