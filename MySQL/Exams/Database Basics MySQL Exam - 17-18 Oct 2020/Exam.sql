#1

CREATE TABLE pictures(
id INT PRIMARY KEY AUTO_INCREMENT,
url VARCHAR(100) NOT NULL,
added_on DATETIME NOT NULL
);

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE products(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE,
best_before DATE,
price DECIMAL(10,2) NOT NULL,
`description` TEXT,
category_id INT NOT NULL,
picture_id INT NOT NULL,
CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_products_pictures
FOREIGN KEY (picture_id)
REFERENCES pictures(id)
);


CREATE TABLE towns(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE addresses(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL UNIQUE,
town_id INT NOT NULL,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE stores(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE,
rating FLOAT NOT NULL,
has_parking TINYINT(1) DEFAULT FALSE,
address_id INT NOT NULL,
CONSTRAINT fk_stores_addresses
FOREIGN KEY (address_id)
REFERENCES addresses(id)
);

CREATE TABLE products_stores(
product_id INT NOT NULL,
store_id INT NOT NULL,
CONSTRAINT pk_products_stores
PRIMARY KEY (product_id,store_id),
CONSTRAINT fk_products_stores_stores
FOREIGN KEY (store_id)
REFERENCES stores(id),
CONSTRAINT fk_products_stores_products
FOREIGN KEY (product_id)
REFERENCES products(id)
);

CREATE TABLE employees(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(15) NOT NULL,
middle_name CHAR(1),
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(19,2) DEFAULT 0 NOT NULL,
hire_date DATE NOT NULL,
manager_id INT,
store_id INT NOT NULL,
CONSTRAINT fk_employees_stores
FOREIGN KEY (store_id)
REFERENCES stores(id),
CONSTRAINT fk_employees
FOREIGN KEY (manager_id)
REFERENCES employees(id)
);

#2.	Insert
INSERT INTO products_stores(product_id, store_id)
SELECT (p.id),(1) FROM products AS p
LEFT JOIN products_stores as ps on ps.product_id=p.id
WHERE ps.product_id is NULL;

#3.	Update
UPDATE employees 
SET manager_id = 3, salary = salary - 500
WHERE year(hire_date) > 2003 AND store_id NOT IN (5, 14);

#4. Delete
DELETE FROM employees
WHERE salary >= 6000 AND manager_id is NOT NULL;

#5.	Employees 
SELECT e.first_name, e.middle_name, e.last_name, e.salary, e.hire_date
FROM employees as e
ORDER BY e.hire_date DESC;

#6.	Products with old pictures
SELECT p.`name`,p.price,p.best_before, concat(substr(p.`description`,1,10),'...'),
		pc.url
FROM products as p
JOIN pictures as pc on pc.id=p.picture_id
WHERE year(pc.added_on) <2019 and p.price >20 and length(p.`description`) > 100
ORDER BY p.price DESC;

#7.	Counts of products in stores and their average 
SELECT s.`name`, count(p.id) as 'product_count', round(avg(p.price),2) as 'average' FROM stores as s
LEFT JOIN products_stores as ps on ps.store_id=s.id
LEFT JOIN products as p on p.id=ps.product_id
GROUP BY s.`name`
ORDER BY product_count DESC, average DESC, s.id;


#8.	Specific employee
SELECT concat_ws(' ',e.first_name, e.last_name) as 'Full_name', 
		s.`name` as 'Store_name', 
        a.`name` as 'address',
        e.salary
FROM employees as e
JOIN stores as s on s.id=e.store_id
JOIN addresses as a on a.id=s.address_id
WHERE e.salary<4000 and a.`name` LIKE '%5%' and length(s.`name`)>8 and e.last_name LIKE '%n';

#9.	Find all information of stores
SELECT reverse(s.`name`) as 'reversed_name', 
		concat_ws('-',upper(t.`name`),a.`name`) as 'full_address',
        count(e.id) as 'employees_count'
FROM stores as s
JOIN addresses as a on a.id=s.address_id
JOIN towns as t on t.id=a.town_id
JOIN employees as e on e.store_id=s.id
GROUP BY s.`name`
ORDER BY full_address;

#10.	Find full name of top paid employee by store name
DELIMITER $$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE full_info VARCHAR (255);
	DECLARE full_name  VARCHAR (40);
	DECLARE years INT;
	DECLARE employee_id INT;
    
	SET employee_id := (
		SELECT e.id
		FROM employees AS e
		JOIN stores AS s
		ON e.store_id = s.id
		WHERE s.`name` = store_name
		ORDER BY e.salary DESC
		LIMIT 1);
        
	SET full_name := (
		SELECT concat_ws(' ', first_name, concat(middle_name, '.'), last_name)
		FROM employees AS e
    		WHERE e.id = employee_id);
	
  	SET years := (
		SELECT floor(DATEDIFF("2020-10-18", hire_date)/365)
		FROM employees AS e
		WHERE e.id = employee_id);
    
  	SET full_info := concat_ws(' ', full_name, 'works in store for', years, 'years');
  	RETURN full_info;
END$$

#11.	Update product price by address
DELIMITER $$
CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50))
BEGIN
UPDATE products AS p
		JOIN products_stores AS ps
		ON ps.product_id = p.id
    		JOIN stores AS s
    		ON ps.store_id = s.id
    		JOIN addresses AS a
    		ON s.address_id = a.id
	SET p.price = IF (left(address_name, 1) = '0', p.price + 100, p.price + 200)
	WHERE a.`name` = address_name;
END$$