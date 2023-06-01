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
  category VARCHAR(255) NOT NULL,
  brand VARCHAR(255) NOT NULL,
  price FLOAT
);

CREATE TABLE `grns` (
`id` INT NOT NULL AUTO_INCREMENT,
`supplier_name` VARCHAR(255) NOT NULL,
`total` FLOAT NOT NULL ,
 `is_shelf` BOOLEAN NOT NULL ,
 PRIMARY KEY (`id`)
 );

 CREATE TABLE `grn_items` (
 `id` INT NOT NULL AUTO_INCREMENT ,
  `grn_id` INT NOT NULL ,
  `product_code` INT NOT NULL ,
  `qty` FLOAT NOT NULL ,
  `cost` FLOAT NOT NULL ,
  PRIMARY KEY (`id`)
  );
