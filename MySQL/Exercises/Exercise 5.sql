#1.	One-To-One Relationship
CREATE DATABASE tablerelation;
USE tablerelation;
CREATE TABLE passports(
passport_id	INT PRIMARY KEY AUTO_INCREMENT,
passport_number varchar(50) UNIQUE
);

CREATE TABLE people(
person_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) not null,
salary DECIMAL(9,2) not null,
passport_id INT UNIQUE,
CONSTRAINT fk_people_passports
FOREIGN KEY people(passport_id)
REFERENCES passports(passport_id)
);

INSERT INTO passports(passport_id,passport_number) VALUES
(101, 'N34FG21B'),
(102, 'K65LO4R7'),
(103, 'ZE657QP2');

INSERT INTO people(person_id, first_name, salary, passport_id) VALUES
(1, 'Roberto', 43300.00, 102),
(2, 'Tom', 56100.00, 103),
(3, 'Yana', 60200.00, 101);


#2.	One-To-Many Relationship
CREATE TABLE manufacturers(
manufacturer_id	INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
established_on DATE
);

CREATE TABLE models(
model_id INT PRIMARY KEY AUTO_INCREMENT,	
`name` VARCHAR(50) NOT NULL,
manufacturer_id INT NOT NULL
);

ALTER TABLE models
ADD CONSTRAINT fk_models_manufacturers
FOREIGN KEY models(manufacturer_id)
REFERENCES manufacturers(manufacturer_id);


INSERT INTO manufacturers(manufacturer_id,`name`,established_on)
VALUES
(1, 'BMW', '1916/03/01'),
(2, 'Tesla', '2003/01/01'),
(3, 'Lada', '1966/05/01');


INSERT INTO models(model_id,`name`,manufacturer_id)
VALUES 
(101, 'X1',	1),
(102, 'i6',	1),
(103, 'Model S', 2),
(104, 'Model X', 2),
(105, 'Model 3', 2),
(106, 'Nova', 3);

#3.	Many-To-Many Relationship
CREATE TABLE students(
student_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE exams(
exam_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE students_exams(
student_id INT NOT NULL,
exam_id INT NOT NULL
);

ALTER TABLE students_exams
ADD CONSTRAINT pk
PRIMARY KEY(student_id,exam_id);

ALTER TABLE students_exams
ADD CONSTRAINT fk_studentsexams_student
FOREIGN KEY (student_id)
REFERENCES students(student_id),
ADD CONSTRAINT fk_studentsexams_exams
FOREIGN KEY (exam_id)
REFERENCES exams(exam_id);

INSERT INTO students(`name`)
value
('Mila'),
('Toni'),
('Ron');

ALTER TABLE exams auto_increment = 101;

INSERT INTO exams(`name`)
value
('Spring MVC'),
('Neo4j'),
('Oracle 11g');

INSERT INTO students_exams(student_id,exam_id)
value
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);

#4.	Self-Referencing
CREATE TABLE teachers(
teacher_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
manager_id INT
);

ALTER TABLE teachers AUTO_INCREMENT = 101;


INSERT INTO teachers(`name`,manager_id)
value
('John', NULL),
('Maya', 106),
('Silvia', 106),
('Ted', 105),
('Mark', 101),
('Greta', 101);

ALTER TABLE teachers
ADD CONSTRAINT fk
FOREIGN KEY (manager_id)
REFERENCES teachers(teacher_id);

#5.	Online Store Database
CREATE DATABASE exercise5;
USE exercise5;
CREATE TABLE item_types(
item_type_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE items(
item_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
item_type_id INT
);

ALTER TABLE items
ADD CONSTRAINT fk
FOREIGN KEY (item_type_id)
REFERENCES item_types(item_type_id);

CREATE TABLE order_items(
order_id INT(11),
item_id INT(11)
);

ALTER TABLE order_items
ADD CONSTRAINT pk
PRIMARY KEY (order_id,item_id);

ALTER TABLE order_items
ADD CONSTRAINT fk_order_items
FOREIGN KEY (item_id)
REFERENCES items(item_id);

CREATE TABLE orders(
order_id INT(11) PRIMARY KEY AUTO_INCREMENT,
customer_id INT(11)
);


ALTER TABLE order_items
ADD CONSTRAINT fk_order_orders
FOREIGN KEY (order_id)
REFERENCES orders(order_id);

CREATE TABLE customers(
customer_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
birthday DATE,
city_id INT(11)
);

ALTER TABLE orders
ADD CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id);

CREATE TABLE cities(
city_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

ALTER TABLE customers
ADD CONSTRAINT FK_customers_cities
foreign key (city_id)
references cities(city_id);

#6.	University Database
CREATE DATABASE university;
use university;
CREATE TABLE majors(
major_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE payments(
payment_id INT(11) PRIMARY KEY AUTO_INCREMENT,
payment_date DATE,
payment_amount DECIMAL(8,2),
student_id INT(11)
);

CREATE TABLE students(
student_id INT(11) PRIMARY KEY AUTO_INCREMENT,
student_number VARCHAR(12),
student_name VARCHAR(50),
major_id INT(11)
);

CREATE TABLE agenda(
student_id INT(11),
subject_id INT(11)
);

CREATE TABLE subjects(
subject_id INT(11) PRIMARY KEY AUTO_INCREMENT,
subject_name VARCHAR(50)
);

ALTER TABLE students
ADD CONSTRAINT fk_students_majors
FOREIGN KEY (major_id)
references majors(major_id);

ALTER TABLE payments
ADD CONSTRAINT fk_payments_students
FOREIGN KEY (student_id)
REFERENCES students(student_id);

ALTER TABLE agenda
ADD CONSTRAINT pk_agenda
primary key(student_id, subject_id);

ALTER TABLE agenda
ADD CONSTRAINT fk_agenda_students
foreign key (student_id)
references students(student_id),
ADD CONSTRAINT fk_agenda_subjects
foreign key (subject_id)
references subjects(subject_id);

#9.	Peaks in Rila
USE geography;
SELECT m.mountain_range, p.peak_name, p.elevation 
FROM mountains as m
JOIN peaks as p
ON p.mountain_id = m.id
WHERE m.mountain_range = 'Rila'
ORDER BY p.elevation DESC;