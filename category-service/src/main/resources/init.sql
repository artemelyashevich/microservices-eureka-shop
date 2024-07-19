use `eureka-shop`;
CREATE TABLE IF NOT EXISTS categories (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (100) NOT NULL,
  created_at DATE NOT NULL
);