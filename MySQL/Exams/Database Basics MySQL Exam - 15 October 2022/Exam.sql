#1

CREATE TABLE products(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL UNIQUE,
`type` VARCHAR(30) NOT NULL,
price DECIMAL(10,2) NOT NULL
);

CREATE TABLE clients(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL,
card VARCHAR(50),
review TEXT
);

CREATE TABLE `tables`(
id INT PRIMARY KEY AUTO_INCREMENT,
floor INT NOT NULL,
reserved TINYINT(1),
capacity INT NOT NULL
);

CREATE TABLE waiters(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
phone VARCHAR(50),
salary DECIMAL(10,2)
);

CREATE TABLE orders(
id INT PRIMARY KEY AUTO_INCREMENT,
table_id INT NOT NULL,
waiter_id INT NOT NULL,
order_time TIME NOT NULL,
payed_status TINYINT(1),
CONSTRAINT fk_orders_waitres
FOREIGN KEY (waiter_id)
REFERENCES waiters(id),
CONSTRAINT fk_orders_tables
FOREIGN KEY (table_id)
REFERENCES `tables`(id)
);

CREATE TABLE orders_clients(
order_id INT,
client_id INT,
CONSTRAINT fk_orders
FOREIGN KEY (order_id)
REFERENCES orders(id),
CONSTRAINT fk_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE orders_products(
order_id INT,
product_id INT,
CONSTRAINT fk_orders_orders
FOREIGN KEY (order_id)
REFERENCES orders(id),
CONSTRAINT fk_products
FOREIGN KEY(product_id)
REFERENCES products(id)
);


#02.	Insert
INSERT INTO products(`name`,`type`, price)
SELECT (concat(w.last_name,' ','specialty')), ('Cocktail'), (ceiling(w.salary*0.01)) FROM waiters as w
WHERE w.id >= 6 and w.salary is NOT NULL;


#03.	Update
UPDATE orders
SET table_id = table_id -1
WHERE id BETWEEN 12 and 23;

#04. DELETE
DELETE w FROM waiters AS w
LEFT JOIN orders as o on o.waiter_id=w.id
WHERE o.waiter_id is NULL;


#05.	Clients
SELECT * FROM clients as cl
ORDER BY birthdate DESC, id DESC;

#06.	Birthdate
SELECT cl.first_name, cl.last_name, cl.birthdate, cl.review FROM clients as cl
WHERE cl.card is NULL and (year(cl.birthdate) BETWEEN 1978 AND 1993)
ORDER BY cl.last_name DESC, cl.id
LIMIT 5;

#07.	Accounts
SELECT 
	concat(w.last_name,w.first_name, length(w.first_name),'Restaurant') as 'username',
    reverse(substring(w.email from 2 for 12)) as 'password'
FROM waiters as w
WHERE w.salary is NOT NULL
ORDER BY `password` DESC;

#08.	Top from menu
SELECT p.id,p.`name`, count(o.id) as 'count'
FROM products as p
JOIN orders_products as op on op.product_id=p.id
JOIN orders as o on o.id=op.order_id
GROUP BY p.`name`
HAVING `count`>=5
ORDER BY `count` DESC, p.`name`;

#09.	Availability
SELECT o.table_id, t.capacity, count(c.id) as 'count_clients',
		CASE 
        WHEN t.capacity > count(c.id) THEN 'Free seats'
        WHEN t.capacity = count(c.id) THEN 'Full'
        WHEN t.capacity < count(c.id) THEN 'Extra seats'
        END as 'availability'
FROM orders as o
LEFT JOIN `tables` as t on t.id=o.table_id
LEFT JOIN orders_clients as oc on oc.order_id=o.id
LEFT JOIN clients as c on c.id=oc.client_id
WHERE t.floor = 1
GROUP BY t.id
ORDER BY o.table_id DESC;

#10.	Extract bill
DELIMITER $$
CREATE FUNCTION udf_client_bill(full_name VARCHAR(50))
RETURNS DECIMAL(19,2)
DETERMINISTIC
BEGIN
	DECLARE total_price DECIMAL(19,2);
    SET total_price := (SELECT sum(p.price) FROM products as p
JOIN orders_products as op on op.product_id=p.id
JOIN orders as o on o.id=op.order_id
JOIN orders_clients as oc on oc.order_id=o.id
JOIN clients as c on c.id=oc.client_id
GROUP BY c.first_name, c.last_name
HAVING concat(c.first_name,' ',c.last_name) = full_name);
RETURN total_price;
END$$
DELIMITER ;


#11
DELIMITER $$
CREATE PROCEDURE udp_happy_hour(type_name VARCHAR(50))
BEGIN
UPDATE products
SET price = price - price*0.2
WHERE `type`= type_name and price >= 10.0;
END$$
DELIMITER ;
