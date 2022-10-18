#8.	Find Full Name
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT concat(ac.first_name ,' ', ac.last_name) AS full_name FROM account_holders as ac
    ORDER BY full_name,id;
END $$

#9.	People with Balance Higher Than
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(money DECIMAL(12,4))
BEGIN
	SELECT ah.first_name, ah.last_name FROM account_holders AS ah
    LEFT JOIN accounts as a ON a.account_holder_id = ah.id
    GROUP BY ah.first_name, ah.last_name
    HAVING SUM(a.balance) > money
    order BY a.account_holder_id;
END$$

#10.	Future Value Function
CREATE FUNCTION ufn_calculate_future_value(sum_number DECIMAL(19,4),interest_rate DOUBLE, number_years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
DECLARE function_sum DECIMAL(19,4);
SET function_sum := sum_number*POW(1+interest_rate,number_years);
RETURN function_sum;
END$$

#11.	Calculating Interest
CREATE PROCEDURE usp_calculate_future_value_for_account(id_account INT, interest_rate DECIMAL(19,4))
BEGIN
	SELECT a.`id` AS 'account_id', ah.`first_name`, ah.`last_name`, 
    a.`balance` AS 'current_balance', ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
    FROM `account_holders` AS ah
    JOIN `accounts` AS a ON a.`account_holder_id` = ah.`id`
    WHERE a.`id` = id_account;
END$$

#12.	Deposit Money
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19,4)) 
BEGIN
	START TRANSACTION;
    IF(money_amount <=0) THEN ROLLBACK;
    ELSE
    UPDATE accounts as a SET a.balance = a.balance + money_amount
    WHERE a.id=account_id;
    END IF;
END$$

#13.	Withdraw Money
CREATE PROCEDURE usp_withdraw_money(id int, money_amount decimal(19,4))
BEGIN
    START TRANSACTION;
    IF (money_amount <= 0 OR (SELECT `balance` FROM accounts AS a WHERE a.`id` = id) < money_amount) THEN
    ROLLBACK;
    ELSE
        UPDATE accounts as ac SET ac.balance = ac.balance - money_amount
        WHERE ac.id = id;
        COMMIT;
    END IF; 
END$$

#14.	Money Transfer
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
IF(amount <= 0 OR (SELECT `balance` from `accounts` where `id` = from_account_id) < amount
    OR from_account_id = to_account_id 
    OR (SELECT COUNT(id) FROM `accounts` WHERE `id` = from_account_id) <> 1
    OR (SELECT COUNT(id) FROM `accounts` WHERE `id` = to_account_id) <> 1) 
    THEN ROLLBACK;
    ELSE
        UPDATE `accounts` SET `balance` = `balance` - amount
        WHERE `id` = from_account_id;
        UPDATE `accounts` SET `balance` = `balance` + amount
        WHERE `id` = to_account_id;
        COMMIT;
    END IF; 
END$$

# 15. Log Accounts Trigger
CREATE TABLE `logs`(
    `log_id` INT PRIMARY KEY AUTO_INCREMENT, 
    `account_id` INT NOT NULL,
    `old_sum` DECIMAL(19, 4) NOT NULL,
    `new_sum` DECIMAL(19, 4) NOT NULL
);

CREATE TRIGGER tr_change_balance
AFTER UPDATE ON `accounts`
FOR EACH ROW
BEGIN
    INSERT INTO `logs`(`account_id`, `old_sum`, `new_sum`) 
    VALUES (OLD.id, OLD.balance, NEW.balance);
END$$
 
# 16. Emails Trigger 
CREATE TABLE `notification_emails`(
    `id` INT PRIMARY KEY AUTO_INCREMENT, 
    `recipient` INT NOT NULL,
    `subject` TEXT,
    `body` TEXT
);

CREATE TRIGGER tr_email_on_change_balance
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
    INSERT INTO `notification_emails`(`recipient`, `subject`, `body`)
    VALUES (NEW.`account_id`, concat_ws(' ', 'Balance change for account:', NEW.`account_id`), concat_ws(' ', 'On', NOW(), 'your balance was changed from', NEW.`old_sum`, 'to', NEW.`new_sum`, '.'));
END$$
 
