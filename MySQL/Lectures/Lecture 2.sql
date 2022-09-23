CREATE DATABASE IF NOT EXISTS `hotel`; 
USE `hotel`;

CREATE TABLE departments (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
);

INSERT INTO departments(name) VALUES('Front Office'), ('Support'), ('Kitchen'), ('Other');

CREATE TABLE employees (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	job_title VARCHAR(50) NOT NULL,
	department_id INT NOT NULL,
	salary DOUBLE NOT NULL,
	CONSTRAINT `fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
);

INSERT INTO `employees` (`first_name`,`last_name`, `job_title`,`department_id`,`salary`) VALUES
	('John', 'Smith', 'Manager',1, 900.00),
	('John', 'Johnson', 'Customer Service',2, 880.00),
	('Smith', 'Johnson', 'Porter', 4, 1100.00),
	('Peter', 'Petrov', 'Front Desk Clerk', 1, 1100.00),
	('Peter', 'Ivanov', 'Sales', 2, 1500.23),
	('Ivan' ,'Petrov', 'Waiter', 3, 990.00),
	('Jack', 'Jackson', 'Executive Chef', 3, 1800.00),
	('Pedro', 'Petrov', 'Front Desk Supervisor', 1, 2100.00),
	('Nikolay', 'Ivanov', 'Housekeeping', 4, 1600.00);
	

	
CREATE TABLE rooms (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`type` VARCHAR(30)
);

INSERT INTO rooms(`type`) VALUES('apartment'), ('single room');

CREATE TABLE clients (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	room_id INT NOT NULL,
    CONSTRAINT fk_clients_rooms
    FOREIGN KEY (room_id)
    REFERENCES rooms(id)
);

INSERT INTO clients(`first_name`,`last_name`,`room_id`) 
VALUES('Pesho','Petrov', 1),('Gosho','Georgiev', 2),
('Mariya','Marieva', 2), ('Katya','Katerinova', 1), ('Nikolay','Nikolaev', 2);


SELECT id,first_name,last_name,job_title FROM employees;

SELECT id,first_name,last_name,job_title FROM employees ORDER BY id;

SELECT id AS '№', concat(first_name,' ',last_name) AS 'Full Name', job_title AS 'Job Title' FROM employees ORDER BY job_title;

SELECT 
	id AS 'id', 
    concat(`first_name`,' ',`last_name`) AS 'full_name', 
    job_title as  'job_title',
    salary AS 'salary' 
FROM employees
WHERE salary >1000;

SELECT DISTINCT department_id FROM employees;


SELECT concat (first_name,' ', last_name) AS 'Full name', job_title, department_id FROM employees WHERE department_id =1;

CREATE VIEW `first_last_name_together` AS SELECT concat(first_name,' ', last_name) AS 'Full name' FROM employees;
SELECT * FROM first_last_name_together;

SELECT first_name, salary FROM employees WHERE salary > 1000;

SELECT id, first_name,last_name, salary FROM employees WHERE salary BETWEEN 2000 AND 2200;

SELECT * FROM employees WHERE department_id = 4 AND salary >= 1000;

SELECT * FROM employees ORDER BY id DESC;

CREATE VIEW salary_employee AS
SELECT id, concat(first_name, ' ', last_name) AS 'Full Name', salary
FROM employees 
ORDER BY id;
SELECT*FROM salary_employee;

CREATE VIEW heigher_salary_employee AS
SELECT id, concat(first_name, ' ', last_name) AS 'Full Name', salary, department_id
FROM employees 
WHERE salary > 1000
ORDER BY salary DESC;
SELECT*FROM heigher_salary_employee;

UPDATE employees SET salary = salary + 100 WHERE job_title = 'Manager';
SELECT salary
FROM employees;

UPDATE employees 
SET last_name = 'Johnsons' 
where last_name = 'Johnson';


CREATE VIEW `top_paid_employee` AS
SELECT
	concat(first_name,' ', last_name) AS 'Full Name', salary
FROM employees
ORDER BY department_id;

CREATE VIEW `top_paid_employee1` AS
SELECT * FROM employees
ORDER BY `salary` DESC LIMIT 1;
SELECT * FROM top_paid_employee1;

SELECT * FROM employees
WHERE department_id = 4 AND salary >= 1000;

DELETE FROM employees WHERE department_id = 1 OR department_id=2;
SELECT * FROM employees;