#1

CREATE TABLE customers(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
phone VARCHAR(30) UNIQUE NOT NULL,
address VARCHAR(60) NOT NULL,
discount_card BIT DEFAULT FALSE NOT NULL
);

CREATE TABLE orders(
id INT PRIMARY KEY AUTO_INCREMENT,
order_datetime DATETIME NOT NULL,
customer_id INT NOT NULL,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(id)
);

CREATE TABLE orders_products(
order_id INT,
product_id INT,
CONSTRAINT fk_orders
FOREIGN KEY (order_id)
REFERENCES orders(id)
);

CREATE TABLE products(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
price DECIMAL(19,2) NOT NULL,
quantity_in_stock INT,
`description` TEXT,
brand_id INT NOT NULL,
category_id INT NOT NULL,
review_id INT
);

ALTER TABLE orders_products
ADD CONSTRAINT fk_products
FOREIGN KEY (product_id)
REFERENCES products(id);

CREATE TABLE reviews(
id INT PRIMARY KEY AUTO_INCREMENT,
content TEXT,
rating DECIMAL(10,2) NOT NULL,
picture_url VARCHAR(80) NOT NULL,
published_at DATETIME NOT NULL
);

CREATE TABLE brands(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) UNIQUE NOT NULL
);

ALTER TABLE products
ADD CONSTRAINT fk_products_brands
FOREIGN KEY (brand_id)
REFERENCES brands(id),
ADD CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
ADD CONSTRAINT fk_products_reviews
FOREIGN KEY (review_id)
REFERENCES reviews(id);

#2 INSERT
INSERT INTO reviews(content,picture_url, published_at, rating)
SELECT LEFT(p.`description`,15), reverse(p.`name`), DATE('2010/10/10'), p.price/8 
FROM products AS p
WHERE p.id>=5;

#3 UPDATE
UPDATE products AS p
SET p.quantity_in_stock = p.quantity_in_stock-5
WHERE p.quantity_in_stock BETWEEN 60 AND 70;

#4 DELETE
DELETE c FROM customers c
LEFT JOIN orders as o on o.customer_id = c.id
WHERE o.customer_id is NULL;

#05.	Categories
SELECT * FROM categories as c ORDER BY c.`name` DESC;

#06.	Quantity
use online_store;
SELECT p.id, p.brand_id, p.name, p.quantity_in_stock FROM products as p
WHERE p.price >1000 and p.quantity_in_stock < 30
ORDER BY p.quantity_in_stock, p.id;

#07.	Review
SELECT * FROM reviews as r
WHERE r.content LIKE 'My%' and length(r.content) > 61
ORDER BY r.rating DESC;

#08.	First customers
SELECT 
	concat(c.first_name,' ' ,c.last_name) AS 'full_name',
    c.address,
    r.order_datetime
FROM customers as c
JOIN orders as r on r.customer_id = c.id
WHERE year(r.order_datetime)<= 2018
ORDER BY full_name DESC;

#09.	Best categories
SELECT 
	COUNT(c.id) as 'items_count', 
    c.`name`, 
    SUM(p.quantity_in_stock) as 'total_quantity' 
FROM categories as c 
JOIN products as p on p.category_id = c.id
GROUP BY c.`name`
ORDER BY items_count DESC, total_quantity
LIMIT 5;

#10.	Extract client cards count
DELIMITER $$
CREATE FUNCTION udf_customer_products_count(name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count_total INT;
    SET count_total:= (SELECT count(c.id) FROM customers as c
JOIN orders as o on o.customer_id=c.id
JOIN orders_products as op on op.order_id = o.id
WHERE c.first_name = name);
RETURN count_total;
END$$
DELIMITER ;


#11.	Reduce price
DELIMITER $$
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
UPDATE products p
JOIN reviews as r on p.review_id = r.id
JOIN categories as c on p.category_id = c.id
SET p.price = p.price * 0.7
WHERE c.`name` = category_name AND r.rating <4;
END$$