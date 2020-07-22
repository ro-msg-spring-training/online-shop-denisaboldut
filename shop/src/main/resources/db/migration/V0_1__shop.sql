DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  email_address VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS supplier;

CREATE TABLE supplier (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS product;

CREATE TABLE product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    price NUMERIC(7,3) NOT NULL,
    weight NUMERIC(7,3) NOT NULL,
    category INT,
    supplier INT,
    image_url VARCHAR(250) NOT NULL
);

 ALTER TABLE product
    ADD FOREIGN KEY (category)
    REFERENCES product_category(id);

  ALTER TABLE product
     ADD FOREIGN KEY (supplier)
     REFERENCES supplier(id);


DROP TABLE IF EXISTS location;

CREATE TABLE location(
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(250) NOT NULL,
      country VARCHAR(250) NOT NULL,
      city VARCHAR(250) NOT NULL,
      street_address VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS order_shop;

CREATE TABLE order_shop(
    id INT AUTO_INCREMENT PRIMARY KEY,
    shipped_from INT,
    customer INT,
    created_at TIMESTAMP NOT NULL,
    country VARCHAR(250) NOT NULL,
    city VARCHAR(250) NOT NULL,
    street_address VARCHAR(250) NOT NULL
);

 ALTER TABLE order_shop
    ADD FOREIGN KEY (shipped_from)
    REFERENCES  location(id);

  ALTER TABLE order_shop
     ADD FOREIGN KEY (customer)
     REFERENCES customer(id);


DROP TABLE IF EXISTS order_detail;

CREATE TABLE order_detail(
   id INT AUTO_INCREMENT PRIMARY KEY,
   order_shop INT,
   product INT,
   quantity INT NOT NULL
);

ALTER TABLE order_detail
     ADD FOREIGN KEY (order_shop)
     REFERENCES order_shop(id);

ALTER TABLE order_detail
     ADD FOREIGN KEY (product)
     REFERENCES product(id);


DROP TABLE IF EXISTS stock;

CREATE TABLE stock(
   id INT AUTO_INCREMENT PRIMARY KEY,
   product INT,
   location INT,
   quantity INT NOT NULL
);

ALTER TABLE stock
     ADD FOREIGN KEY (product)
     REFERENCES product(id);

ALTER TABLE stock
     ADD FOREIGN KEY (location)
     REFERENCES location(id);


DROP TABLE IF EXISTS revenue;

CREATE TABLE revenue(
    id INT AUTO_INCREMENT PRIMARY KEY,
    location INT,
    date_revenue DATE NOT NULL,
    sum_total NUMERIC(7,3) NOT NULL
  );

  ALTER TABLE revenue
       ADD FOREIGN KEY (location)
       REFERENCES location(id);