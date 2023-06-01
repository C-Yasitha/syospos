-- V1__Create_Users_Table.sql

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users(username,first_name,last_name,password) VALUES ('admin','Admin','User','123');

CREATE TABLE categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE brands (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_code VARCHAR(255) UNIQUE NOT NULL,
  product_name VARCHAR(255) NOT NULL,
  product_description VARCHAR(255),
  product_image VARCHAR(255),
  low_level INT,
  is_service BOOLEAN,
  product_weight DECIMAL(10,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  category_id INT,
  brand_id INT,
  price FLOAT,
  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (brand_id) REFERENCES brands(id)
);
