USE soft_uni;

#1.	Count Employees by Town
DELIMITER $$

CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20))
RETURNS INT 
DETERMINISTIC
BEGIN
	DECLARE e_count INT;
	SET e_count := (SELECT COUNT(employee_id) FROM employees AS e
	 JOIN addresses AS a ON a.address_id = e.address_id
	 JOIN towns AS t ON t.town_id = a.town_id
	WHERE t.name = town_name);
	RETURN e_count;
END$$

SELECT ufn_count_employees_by_town('Sofia')$$

#2.	Employees Promotion
CREATE PROCEDURE usp_raise_salaries(department_name varchar(50))
BEGIN
	UPDATE employees AS e 
	 JOIN departments AS d 
	ON e.department_id = d.department_id 
	SET salary = salary * 1.05
	WHERE d.name = department_name;
END $$


CALL usp_raise_salaries('Sales')$$
 
 
#3. Employees Promotion by ID
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
	START TRANSACTION;
	IF((SELECT count(employee_id) FROM employees WHERE employee_id like id)<>1) THEN
	ROLLBACK;
	ELSE
		UPDATE employees AS e SET salary = salary + salary*0.05 
		WHERE e.employee_id = id;
	END IF; 
END$$


#4. Triggered
CREATE TABLE deleted_employees(
	employee_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	middle_name VARCHAR(20),
	job_title VARCHAR(50),
	department_id INT,
	salary DOUBLE 
);

CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON employees
FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees (first_name,last_name, middle_name,job_title,department_id,salary)
	VALUES(OLD.first_name,OLD.last_name,OLD.middle_name, OLD.job_title,OLD.department_id,OLD.salary);
END;
