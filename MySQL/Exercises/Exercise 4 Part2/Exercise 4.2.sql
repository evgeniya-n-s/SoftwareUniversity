#12.	 Employees Minimum Salaries
SELECT department_id, MIN(salary) AS `minimum_salary`
FROM employees
WHERE hire_date > 2000-01-01
GROUP BY department_id
HAVING department_id IN(2,5,7)
ORDER BY department_id;

#13.	Employees Average Salaries
USE soft_uni;
CREATE TABLE employees_average_salaries
SELECT * FROM employees
WHERE salary > 30000 and manager_id!= 42;

UPDATE employees_average_salaries
SET salary = salary + 5000
WHERE department_id = 1;


SELECT department_id, AVG(salary) AS `avg_salary`
FROM employees_average_salaries
GROUP BY department_id
ORDER BY department_id;

#14. Employees Maximum Salaries
SELECT department_id, MAX(salary) AS `max_salary`
FROM employees
GROUP BY department_id
HAVING `max_salary` < 30000 OR `max_salary` > 70000
ORDER BY department_id;

#15.	Employees Count Salaries
SELECT COUNT(employee_id) AS ``
FROM employees
WHERE manager_id is NULL;

#16.	3rd Highest Salary*
SELECT DISTINCT `department_id`,
        (
        SELECT  DISTINCT `salary`
        FROM    `employees` e
        WHERE   e.`department_id` = `employees`.`department_id`
        ORDER BY `salary` DESC
        LIMIT 1 OFFSET 2
        ) AS `third_highest_salary`
FROM    `employees`
HAVING `third_highest_salary` IS NOT NULL
ORDER BY `department_id`;


#17.	 Salary Challenge**
SELECT `first_name`, `last_name`, `department_id`
FROM `employees` f
WHERE `salary` > (
    SELECT avg(`salary`)
    FROM `employees` e
    WHERE e.department_id = f.department_id
    )
ORDER BY `department_id`, `employee_id`
LIMIT 10;


#18.	Departments Total Salaries
SELECT department_id, SUM(salary) AS `total_salary`
FROM employees
GROUP BY department_id
ORDER BY department_id;