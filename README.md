****Bank Management System 🏦
A simple Java-based Banking System with a MySQL database backend.
Supports basic banking operations like account creation, login, balance management, and transaction history****.

**Features**
  ✅ Create new bank accounts (Savings / Current)
  ✅ Login to an existing account
  ✅ View account details
  ✅ Deposit / Withdraw funds
  ✅ View transaction history
  ✅ Error handling: shows "Account already exists" for duplicate account attempts
  ✅ Uses JDBC to connect to MySQL database
  ✅ Uses prepared statements to prevent SQL injection

**Tech Stack**
  -> Java 17+
  -> MySQL 8.0+
  -> JDBC
  -> Maven or Manual Build (optional)
  -> Loggers: Java built-in logging

How to Run
1️⃣ **Setup Database**

  CREATE DATABASE bank_system;
  
  USE bank_system;
  
  CREATE TABLE accounts (
      acc_no BIGINT PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      pin INT NOT NULL,
      type VARCHAR(10),
      balance DOUBLE
  );
  
  CREATE TABLE transactions (
      id INT AUTO_INCREMENT PRIMARY KEY,
      acc_no BIGINT,
      type VARCHAR(20),
      amount DOUBLE,
      timestamp DATETIME,
      FOREIGN KEY (acc_no) REFERENCES accounts(acc_no)
  );


2️⃣ **Update DB Credentials in DbUtil.java**

  private static final String db_url = "jdbc:mysql://localhost:3306/bank_system";
  private static final String db_user = "root";
  private static final String db_password = "your_password_here";


3️⃣ **Compile & Run**

**bash**

  javac -d bin src/bank/*.java
  java -cp bin bank.Main
  
**Future Improvements**
  -> Add multi-threading for concurrent users
  -> Implement REST API using Spring Boot
  -> Add Admin panel
  -> Password/PIN encryption
  -> Dockerize the app for deployment
