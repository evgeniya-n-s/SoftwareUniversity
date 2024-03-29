CREATE DATABASE minions;

CREATE TABLE minions(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT
);

CREATE TABLE towns(
	town_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

ALTER TABLE `towns` 
CHANGE COLUMN `town_id` `id` INT(11) NOT NULL AUTO_INCREMENT ;


ALTER TABLE `minions`.`minions`
ADD COLUMN `town_id` INT;


ALTER TABLE `minions` 
ADD CONSTRAINT `fk_minions_towns`
  FOREIGN KEY `minions`(`town_id`)
  REFERENCES `towns` (`id`);
  

INSERT INTO towns (id,name)
VALUES (1,'Sofia'),(2,'Plovdiv'),(3,'Varna');


INSERT INTO minions (id, name, age, town_id)
VALUES 
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL , 2);


TRUNCATE TABLE minions;

DROP TABLE minions;
DROP TABLE towns;



CREATE TABLE people(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(200) NOT NULL,
picture BLOB,
height DOUBLE(10,2),
weight DOUBLE(10,2),
gender CHAR(1) NOT NULL,
birthdate DATE NOT NULL,
biography TEXT
);



INSERT INTO people(name,gender,birthdate)
VALUES
('Ivan', 'm', DATE(NOW())),
('Boris', 'm', DATE(NOW())),
('Ivanka', 'f', DATE(NOW())),
('Desi', 'f', DATE(NOW())),
('Dancho', 'm', DATE(NOW()));



CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(30) NOT NULL,
password VARCHAR(26) NOT NULL,
profile_picture BLOB,
last_login_time TIME,
is_deleted BOOLEAN
);

INSERT INTO `users` (`username`, `password`)
VALUES 
('Ivan123', 'Pass1'),
('Petko2', 'Pass2'),
('DesiTo', 'Pass3'),
('Ivanka', 'Pass4'),
('Petio', 'Pass5');


ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY users(id,username);


ALTER TABLE users
CHANGE COLUMN last_login_time last_login_time DATETIME DEFAULT NOW();

ALTER TABLE users
MODIFY COLUMN last_login_time DATETIME DEFAULT NOW();


ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY users(id),
CHANGE COLUMN username username VARCHAR(50) UNIQUE;

CREATE DATABASE movies;

USE movies;
CREATE TABLE directors(
id INT PRIMARY KEY AUTO_INCREMENT,
director_name VARCHAR(50) NOT NULL,
notes TEXT
);

INSERT INTO directors (director_name, notes)
VALUES 
('Ivan','TestNotes'), 
('Dragan','TestNotes'),
('Petkan','TestNotes'),
('Ivanka','TestNotes'),
('Desi','TestNotes');

CREATE TABLE genres(
id INT PRIMARY KEY AUTO_INCREMENT,
genre_name VARCHAR(20) NOT NULL,
notes TEXT
);

INSERT INTO genres(genre_name, notes)
VALUES
('Ivan','TestNotes'), 
('Dragan','TestNotes'),
('Petkan','TestNotes'),
('Ivanka','TestNotes'),
('Desi','TestNotes');

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
category_name VARCHAR(20) NOT NULL,
notes TEXT
);


INSERT INTO categories(category_name, notes)
VALUES
('Funny','TestNotes'), 
('Horror','TestNotes'),
('Drama','TestNotes'),
('Romantic','TestNotes'),
('Kids','TestNotes');



CREATE TABLE movies(
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(40) NOT NULL,
director_id INT,
copyright_year INT,
length INT,
genre_id INT,
category_id INT,
rating DOUBLE,
notes TEXT
);

INSERT INTO movies(title)
VALUES
('Test'),
('Test1'),
('Test2'),
('Test3'),
('Test4');


CREATE DATABASE car_rental;
USE car_rental;

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
category VARCHAR(50), 
daily_rate DOUBLE, 
weekly_rate DOUBLE, 
monthly_rate DOUBLE, 
weekend_rate DOUBLE
);

INSERT INTO categories(category)
VALUES
('Test'),
('Test1'),
('Test2')
;

CREATE TABLE cars(
id INT PRIMARY KEY AUTO_INCREMENT, 
plate_number INT, 
make VARCHAR(50), 
model VARCHAR(50), 
car_year INT, 
category_id INT, 
doors INT, 
picture BLOB, 
car_condition VARCHAR(50), 
available BOOLEAN
);

INSERT INTO cars(plate_number)
VALUES
(1),
(2),
(3)
;

CREATE TABLE employees (
id INT PRIMARY KEY AUTO_INCREMENT, 
first_name VARCHAR(50), 
last_name VARCHAR(50), 
title VARCHAR(50), 
notes TEXT
);

INSERT INTO employees(first_name, last_name)
VALUES
('Ivan','Ivanov'),
('Georgi','Georgiev'),
('Desi', 'Vladimirova')
;



CREATE TABLE customers (
id INT PRIMARY KEY AUTO_INCREMENT, 
driver_licence_number INT, 
full_name VARCHAR(50), 
address VARCHAR(50), 
city VARCHAR(50), 
zip_code VARCHAR(20), 
notes TEXT
);

INSERT INTO customers(driver_licence_number,full_name)
VALUES
(1234,'Ivan'),
(5678,'Georgi'),
(91011,'Peter')
;



CREATE TABLE rental_orders (
id INT PRIMARY KEY AUTO_INCREMENT, 
employee_id INT, 
customer_id INT, 
car_id INT, 
car_condition VARCHAR(50), 
tank_level VARCHAR(50), 
kilometrage_start DOUBLE, 
kilometrage_end DOUBLE, 
total_kilometrage DOUBLE, 
start_date DATE, 
end_date DATE, 
total_days INT, 
rate_applied DOUBLE, 
tax_rate DOUBLE, 
order_status VARCHAR(50), 
notes TEXT
);

INSERT INTO rental_orders(employee_id,customer_id)
VALUES
(1234,4784),
(9856,484),
(852,474)
;

CREATE DATABASE soft_uni;
USE soft_uni;

CREATE TABLE towns (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL
);

CREATE TABLE addresses (
id INT PRIMARY KEY AUTO_INCREMENT, 
address_text VARCHAR(50) NOT NULL, 
town_id INT
);

CREATE TABLE departments (
id INT PRIMARY KEY AUTO_INCREMENT, 
name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
id INT PRIMARY KEY AUTO_INCREMENT, 
first_name VARCHAR(50) NOT NULL, 
middle_name VARCHAR(50), 
last_name VARCHAR(50) NOT NULL, 
job_title VARCHAR(50), 
department_id INT NOT NULL, 
hire_date DATE, 
salary DOUBLE, 
address_id INT,
CONSTRAINT fk_employees_departments 
    FOREIGN KEY `employees`(`department_id`) 
    REFERENCES `departments`(`id`),
    CONSTRAINT fk_employees_addresses 
    FOREIGN KEY `employees`(`address_id`)
    REFERENCES `addresses`(`id`)
);

INSERT INTO towns(name)
VALUES
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas')
;

INSERT INTO departments(name)
VALUES
('Engineering'), 
('Sales'), 
('Marketing'), 
('Software Development'), 
('Quality Assurance')
;

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`,`salary`)
VALUES
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);

SELECT * FROM towns;
SELECT * FROM departments;
SELECT * FROM employees;


SELECT *FROM towns
ORDER BY name;
SELECT *FROM departments
ORDER BY name;
SELECT *FROM employees
ORDER BY salary DESC;

SELECT `name` FROM `towns`
ORDER BY `name`;
SELECT `name` FROM `departments`
ORDER BY `name`;
SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees`
ORDER BY `salary` DESC;

UPDATE employees
SET salary = salary*1.1;
SELECT salary FROM employees;
