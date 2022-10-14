#1.	Employees with Salary Above 35000
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT e.first_name, e.last_name from employees AS e
    WHERE e.salary > 35000
    ORDER BY e.first_name,e.last_name, e.employee_id;
END $$

CALL usp_get_employees_salary_above_35000() $$


#2.	Employees with Salary Above Number
CREATE PROCEDURE usp_get_employees_salary_above(input_number DECIMAL(10,4))
BEGIN
	SELECT e.first_name, e.last_name from employees AS e
    Where e.salary >= input_number
    ORDER BY e.first_name,e.last_name, e.employee_id;
END $$

CALL usp_get_employees_salary_above(45000) $$


#3.	Town Names Starting With
CREATE PROCEDURE usp_get_towns_starting_with(IN input VARCHAR(50))
BEGIN
	SELECT t.`name` FROM towns AS t
    Where t.`name` LIKE concat(input,'%')
    ORDER BY t.`name`;
END $$

CALL usp_get_towns_starting_with("b") $$

#4.	Employees from Town
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT e.first_name, e.last_name FROM employees as e
    JOIN addresses as a ON a.address_id = e.address_id
    JOIN towns as t on t.town_id = a .town_id
    WHERE t.`name` = town_name
    ORDER BY e.first_name,e.last_name, e.employee_id;
END $$

CALL usp_get_employees_from_town('Sofia')$$

#5.	Salary Level Function
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(19,4))
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(50);
    IF salary < 30000 THEN SET salary_level := 'Low';
    ELSEIF salary BETWEEN 30000 and 50000 THEN SET salary_level := 'Average';
    ELSE SET salary_level := 'High';
    END IF;
RETURN salary_level;
END $$

SELECT ufn_get_salary_level(13500.00)$$

#6.	Employees by Salary Level
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(20))
BEGIN
	SELECT e.first_name, e.last_name FROM employees as e
    Where ufn_get_salary_level(e.salary) LIKE salary_level
	ORDER BY e.first_name DESC,e.last_name DESC;
END$$

CALL usp_get_employees_by_salary_level('high')$$

#7.	Define Function
CREATE function ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN word REGEXP(concat('^[', set_of_letters, ']+$'));
END$$