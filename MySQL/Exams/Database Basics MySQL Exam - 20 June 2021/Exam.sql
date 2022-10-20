CREATE TABLE addresses(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL
);

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE clients(
id INT PRIMARY KEY AUTO_INCREMENT,
full_name VARCHAR(50) NOT NULL,
phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE drivers(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
age INT NOT NULL,
rating FLOAT DEFAULT 5.5
);

CREATE TABLE cars(
id INT PRIMARY KEY AUTO_INCREMENT,
make VARCHAR(20) NOT NULL,
model VARCHAR(20),
`year` INT DEFAULT 0 NOT NULL,
mileage INT DEFAULT 0,
`condition` CHAR(1) NOT NULL,
category_id INT NOT NULL,
CONSTRAINT pk_cars_categories
FOREIGN KEY (category_id)
REFERENCES categories(id)
);

CREATE TABLE courses(
id INT PRIMARY KEY AUTO_INCREMENT,
from_address_id INT NOT NULL,
`start` DATETIME NOT NULL,
bill DECIMAL(10,2) DEFAULT 0,
car_id INT NOT NULL,
client_id INT NOT NULL,
CONSTRAINT fk_courses_addresses
FOREIGN KEY (from_address_id)
REFERENCES addresses(id),
CONSTRAINT fk_courses_cars
FOREIGN KEY (car_id)
REFERENCES cars(id),
CONSTRAINT fk_courses_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE cars_drivers(
car_id INT NOT NULL,
driver_id INT NOT NULL,
CONSTRAINT pk_cars_drivers
PRIMARY KEY (car_id, driver_id),
CONSTRAINT fk_cars
FOREIGN KEY (car_id)
REFERENCES cars(id),
CONSTRAINT fk_drivers
FOREIGN KEY (driver_id)
REFERENCES drivers(id)
);

#2.	Insert
INSERT INTO clients(full_name,phone_number)
SELECT (concat(first_name, ' ', last_name)),(concat('(088) 9999',id*2)) FROM drivers
WHERE id BETWEEN 10 and 20;

#3.	Update
UPDATE cars AS c
SET c.`condition` = 'C'
WHERE (c.mileage>= 800000 OR c.mileage is NULL) and c.`year`<= 2010 and c.make NOT LIKE 'Mercedes-Benz';

#4.	Delete
DELETE c FROM clients as c
LEFT JOIN courses as co on co.client_id=c.id
WHERE co.client_id is NULL and length(c.full_name)>3;

#5.	Cars
SELECT c.make,c.model,c.`condition` FROM cars as c
ORDER BY c.id;

#6.	Drivers and Cars
SELECT d.first_name,d.last_name, c.make,c.model,c.mileage 
FROM drivers as d
JOIN cars_drivers AS cd on cd.driver_id=d.id
JOIN cars as c on c.id=cd.car_id
WHERE c.mileage is NOT NULL
ORDER BY c.mileage DESC, d.first_name;

#7.	Number of courses for each car
SELECT c.id as 'cars_id', c.make,c.mileage,
		COUNT(co.id) as 'count_of_courses', round(avg(co.bill),2) as 'avg_bill'
FROM cars as c
LEFT JOIN courses as co on co.car_id=c.id
GROUP BY c.id
HAVING count_of_courses <> 2
ORDER BY count_of_courses DESC, c.id;

#8.	Regular clients
SELECT cl.full_name,
		count(c.id) AS 'count_of_cars',
        sum(co.bill)
FROM clients as cl
JOIN courses as co on co.client_id = cl.id
JOIN cars as c on c.id = co.car_id
GROUP BY cl.id
HAVING substr(cl.full_name,2,1)='a' and count_of_cars >1
ORDER BY cl.full_name;

#9.	Full information of courses
SELECT a.`name`,
		CASE
        WHEN hour(co.`start`) BETWEEN 6 and 20 THEN 'Day'
        ELSE 'Night'
        END as day_time, co.bill,
        cl.full_name,
        c.make, c.model,
        ca.`name` as 'category_name'
FROM addresses as a
JOIN courses as co on a.id=co.from_address_id
JOIN clients as cl on cl.id=co.client_id
JOIN cars as c on c.id=co.car_id
JOIN categories as ca on ca.id=c.category_id
ORDER BY co.id;

#10.	Find all courses by client’s phone number
DELIMITER $$
CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20))
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
	DECLARE number_courses VARCHAR(20);
    SET number_courses := (SELECT 
	count(c.id) as 'count'
FROM clients as c
JOIN courses as co on co.client_id=c.id
WHERE c.`phone_number` LIKE phone_num
GROUP BY c.full_name);
RETURN number_courses;
END$$
DELIMITER ;


#11.	Full info for address
DELIMITER $$
CREATE PROCEDURE udp_courses_by_address (address_name VARCHAR(100))
BEGIN
SELECT a.`name`,
		cl.full_name as 'full_names',
        CASE
        WHEN co.bill <=20 THEN 'Low'
        WHEN co.bill <=30 THEN 'Medium'
        ELSE 'High'
        END as'level_of_bill',
        c.make, c.`condition`,
        ca.`name` as 'car_name'
FROM addresses as a
JOIN courses as co on co.from_address_id=a.id
JOIN clients as cl on cl.id=co.client_id
JOIN cars as c on c.id=co.car_id
JOIN categories as ca on ca.id=c.category_id
WHERE a.`name` LIKE address_name
ORDER BY c.make, cl.full_name;
END$$