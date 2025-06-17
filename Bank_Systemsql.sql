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