#1

CREATE TABLE branches(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE employees(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10,2) NOT NULL,
started_on DATE NOT NULL,
branch_id INT NOT NULL,
CONSTRAINT pk_employees_branches
FOREIGN KEY (branch_id)
REFERENCES branches(id)
);

CREATE TABLE clients(
id INT PRIMARY KEY AUTO_INCREMENT,
full_name VARCHAR(50) NOT NULL,
age INT NOT NULL
);

CREATE TABLE employees_clients(
employee_id INT,
client_id INT,
CONSTRAINT fk_employees
FOREIGN KEY (employee_id)
REFERENCES employees(id),
CONSTRAINT fk_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE bank_accounts(
id INT PRIMARY KEY AUTO_INCREMENT,
account_number VARCHAR(10) NOT NULL,
balance DECIMAL(10,2) NOT NULL,
client_id INT NOT NULL UNIQUE,
CONSTRAINT fk_accounts_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE cards(
id INT PRIMARY KEY AUTO_INCREMENT,
card_number VARCHAR(19) NOT NULL,
card_status VARCHAR(7) NOT NULL,
bank_account_id INT NOT NULL,
CONSTRAINT fk_cards_accounts
FOREIGN KEY (bank_account_id)
REFERENCES bank_accounts(id)
);

#02.	Insert
INSERT INTO cards(card_number,card_status,bank_account_id)
SELECT (reverse(c.full_name)),('Active'),(c.id) FROM clients as c
WHERE c.id BETWEEN 191 AND 200;

#03.	Update
UPDATE employees_clients
SET employee_id = (SELECT * 
                   FROM (SELECT employee_id 
			FROM employees_clients
                   	GROUP BY employee_id
                   	ORDER BY count(client_id), employee_id
                   	LIMIT 1) 
                   AS s)
WHERE client_id = employee_id;

#04.	Delete
DELETE e FROM employees as e
LEFT JOIN employees_clients as ec on ec.employee_id=e.id
WHERE ec.client_id is NULL;

#05.	Clients
SELECT c.id, c.full_name FROM clients as c ORDER BY c.id;

#06.	Newbies
SELECT e.id, concat(e.first_name,' ',e.last_name) as 'full_name', concat('$',e.salary), e.started_on 
FROM employees as e
WHERE e.salary >= 100000 and year(e.started_on)>=2018
ORDER BY e.salary DESC, e.id;

#07.	Cards against Humanity
SELECT c.id, concat(c.card_number,' : ',cl.full_name) as 'card_token' FROM cards as c
JOIN bank_accounts as bc on bc.id=c.bank_account_id
JOIN clients as cl on cl.id=bc.client_id
ORDER BY c.id DESC;

#08.	Top 5 Employees
SELECT concat(e.first_name,' ',e.last_name) as 'name',e.started_on, count(ec.client_id) as 'count_of_clients' FROM employees as e
RIGHT JOIN employees_clients as ec on ec.employee_id=e.id
GROUP BY e.id
ORDER BY count_of_clients DESC, e.id
LIMIT 5;

#09.	Branch cards
SELECT b.`name`, count(ca.id) AS count_of_cards
FROM branches AS b
LEFT JOIN employees AS e on e.branch_id = b.id
LEFT JOIN employees_clients AS ec on e.id = ec.employee_id
LEFT JOIN clients AS c on ec.client_id = c.id
LEFT JOIN bank_accounts AS ba on c.id = ba.client_id
LEFT JOIN cards AS ca on ba.id = ca.bank_account_id
GROUP BY b.`name`
ORDER BY count_of_cards DESC, b.`name`;

#10.	Extract client cards count
DELIMITER $$
CREATE FUNCTION udf_client_cards_count(`name` VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE number_of_cards INT;
    SET number_of_cards := (SELECT count(c.id) as 'cards' FROM cards as c
LEFT JOIN bank_accounts as ba on ba.id=c.bank_account_id
LEFT JOIN clients as cl on cl.id=ba.client_id
wHERE cl.full_name LIKE `name`);
RETURN number_of_cards;
END$$


#11.	Extract Client Info
CREATE PROCEDURE udp_clientinfo(full_name VARCHAR(30))
BEGIN
SELECT cl.full_name, cl.age, ba.account_number,concat('$',ba.balance) FROM clients as cl
JOIN bank_accounts as ba on ba.client_id=cl.id
WHERE cl.full_name LIKE full_name;
END$$

