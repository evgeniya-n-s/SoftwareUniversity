#1.	Table Design
CREATE TABLE countries(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE towns(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
country_id INT NOT NULL,
CONSTRAINT fk_towns_countries
FOREIGN KEY (country_id)
REFERENCES countries(id)
);

CREATE TABLE stadiums(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
capacity INT NOT NULL,
town_id INT NOT NULL,
CONSTRAINT fk_stadiums_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE teams(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
established DATETIME NOT NULL,
fan_base BIGINT DEFAULT 0 NOT NULL,
stadium_id INT NOT NULL,
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (stadium_id)
REFERENCES stadiums(id)
);

CREATE TABLE skills_data(
id INT PRIMARY KEY AUTO_INCREMENT,
dribbling INT DEFAULT 0,
pace INT DEFAULT 0,
passing INT DEFAULT 0,
shooting INT DEFAULT 0,
speed INT DEFAULT 0,
strength INT DEFAULT 0
);

CREATE TABLE coaches(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10,2) NOT NULL,
coach_level INT DEFAULT 0 NOT NULL
);

CREATE TABLE players(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
age INT DEFAULT 0 NOT NULL,
`position` CHAR(1) NOT NULL,
salary DECIMAL(10,2) DEFAULT 0 NOT NULL,
hire_date DATE,
skills_data_id INT NOT NULL,
team_id INT,
CONSTRAINT fk_players_skills_data
FOREIGN KEY (skills_data_id)
REFERENCES skills_data(id),
CONSTRAINT fk_players_teams
FOREIGN KEY (team_id)
REFERENCES teams(id)
);

CREATE TABLE players_coaches (
	player_id INT,
	coach_id INT,
    	KEY pk_players_coaches (player_id, coach_id),
	CONSTRAINT fk_player
	FOREIGN KEY (player_id)    
    	REFERENCES players (id),
	CONSTRAINT fk_coach
	FOREIGN KEY (coach_id) 
    	REFERENCES coaches (id)
);


#2.	Insert
INSERT INTO coaches(first_name, last_name, salary, coach_level)
SELECT p.first_name, p.last_name, p.salary * 2, length(p.first_name) FROM players as p
WHERE p.age >=45;

#3.	Update
UPDATE coaches as c
LEFT JOIN players_coaches as pc on pc.coach_id = c.id
SET c.coach_level = c.coach_level + 1
WHERE c.first_name LIKE 'A%' AND pc.coach_id is NOT NULL;

#4.	Delete
-- 04. Delete
-- in Workbench
DELETE p
FROM players AS p
JOIN coaches AS c
ON c.first_name = p.first_name AND c.last_name = p.last_name;

-- in Judge
DELETE FROM players
WHERE age >= 45;

#5.	Players 
SELECT p.first_name, p.age, p.salary FROM players as p ORDER BY salary DESC;

#6.	Young offense players without contract
SELECT 
	p.id, 
    concat(p.first_name,' ',p.last_name) AS 'full_name', 
    p.age, 
    p.`position`, 
    p.hire_date 
from players as p
JOIN skills_data as sd on sd.id = p.skills_data_id
WHERE p.age < 23 and p.`position` = 'A' and p.hire_date is NULL and sd.strength > 50
ORDER BY p.salary, age;

#7.	Detail info for all teams
SELECT 
	t.`name` as 'team_name', 
    t.established, 
    t.fan_base, 
    COUNT(p.id) as 'players_count'
FROM teams as t
LEFT JOIN players as p on p.team_id=t.id
GROUP BY t.id
ORDER BY players_count DESC, t.fan_base DESC;

#8.	The fastest player by towns
SELECT 
	MAX(sd.speed) AS 'max_speed', 
    tw.`name` 
FROM skills_data as sd
RIGHT JOIN players as p on p.skills_data_id=sd.id
RIGHT JOIN teams as t on t.id=p.team_id
RIGHT JOIN stadiums as s on s.id=t.stadium_id
RIGHT JOIN towns as tw on tw.id=s.town_id
WHERE t.`name` not like 'Devify'
GROUP BY tw.`name`
ORDER BY max_speed DESC, tw.`name`;

#9.	Total salaries and players by country
SELECT 
	c.`name`, 
    COUNT(p.id) AS 'total_count_of_players', 
    sum(p.salary) AS 'total_sum_of_salaries'
FROM countries as c
LEFT JOIN towns as t on t.country_id = c.id
LEFT JOIN stadiums as s on s.town_id = t.id
LEFT JOIN teams as tm on tm.stadium_id = s.id
LEFT JOIN players as p on p.team_id=tm.id
GROUP BY c.`name`
ORDER BY total_count_of_players DESC, c.`name`;

#10.	Find all players that play on stadium
DELIMITER $$
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
DECLARE count_name INT;
SET count_name :=(
SELECT 
	count(p.id) 
FROM players as p
JOIN teams as t on t.id = p.team_id
JOIN stadiums as s on s.id = t.stadium_id
WHERE s.`name` = stadium_name);
RETURN count_name;
END$$

#11.	Find good playmaker by teams
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT, team_name VARCHAR(45))
BEGIN
SELECT 
	concat(p.first_name,' ', p.last_name) as 'full_name',
    p.age,
    p.salary,
    sd.dribbling,
    sd.speed,
    t.`name` as 'team_name'
FROM players as p
JOIN skills_data as sd on sd.id = p.skills_data_id
JOIN teams as t on t.id=p.team_id
WHERE sd.dribbling > min_dribble_points and t.`name` = team_name and sd.speed > (SELECT AVG(speed) FROM skills_data)
ORDER BY sd.speed DESC
LIMIT 1;
END$$