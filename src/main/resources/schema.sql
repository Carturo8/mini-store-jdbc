-- Create a database
CREATE DATABASE IF NOT EXISTS mini_store_db;
USE mini_store_db;

-- Create table
CREATE TABLE IF NOT EXISTS products
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(150)   NOT NULL UNIQUE,
    price DECIMAL(10, 2) NOT NULL,
    stock INT            NOT NULL
);