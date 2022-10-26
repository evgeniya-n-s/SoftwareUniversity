#1
CREATE TABLE planets(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE spaceports(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
planet_id INT,
CONSTRAINT fk_spaceports_planets
FOREIGN KEY (planet_id)
REFERENCES planets(id)
);

CREATE TABLE spaceships(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
manufacturer VARCHAR(30) NOT NULL,
light_speed_rate INT DEFAULT 0
);

CREATE TABLE colonists(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
ucn CHAR(10) NOT NULL UNIQUE,
birth_date DATE NOT NULL
);

CREATE TABLE journeys(
id INT PRIMARY KEY AUTO_INCREMENT,
journey_start DATETIME NOT NULL,
journey_end DATETIME NOT NULL,
purpose ENUM('Medical', 'Technical', 'Educational', 'Military'),
destination_spaceport_id INT,
spaceship_id INT,
CONSTRAINT fk_journeys_spaceports
FOREIGN KEY (destination_spaceport_id)
REFERENCES spaceports(id),
CONSTRAINT fk_journeys_spaceships
FOREIGN KEY (spaceship_id)
REFERENCES spaceships(id)
);

CREATE TABLE travel_cards(
id INT PRIMARY KEY AUTO_INCREMENT,
card_number CHAR(10) NOT NULL UNIQUE,
job_during_journey ENUM ('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook'),
colonist_id INT,
journey_id INT,
CONSTRAINT fk_travel_cards_colonists
FOREIGN KEY (colonist_id)
REFERENCES colonists(id),
CONSTRAINT fk_travel_cards_journeys
FOREIGN KEY (journey_id)
REFERENCES journeys(id)
);

#01.	Data Insertion
INSERT INTO travel_cards(card_number,job_during_journey,colonist_id,journey_id)
SELECT (CASE 
		WHEN c.birth_date >= '1980-01-01' THEN concat(year(c.birth_date),day(c.birth_date),left(c.ucn,4)) 
        else concat(year(c.birth_date),month(c.birth_date),right(c.ucn,4)) 
        END) as card_number,
        (CASE
        WHEN c.id %2=0 THEN 'Pilot'
        WHEN c.id%3=0 THEN 'Cook'
        ELSE 'Engineer'
        END) job_during_journey,
        c.id,
        (left(c.ucn,1)) as journey_id
FROM colonists as c
WHERE c.id BETWEEN 96 and 100;

#02.	Data Update
UPDATE journeys
SET purpose = (CASE
				WHEN id%2=0 THEN 'Medical'
                WHEN id%3=0 THEN 'Technical'
                WHEN id%5=0 THEN 'Educational'
                WHEN id%7=0 THEN 'Military'
                END)
WHERE id % 2 = 0 OR id % 3 = 0 OR id % 5 = 0 OR id % 7 = 0;

#03.	Data Deletion
DELETE c FROM colonists as c
LEFT JOIN travel_cards as tc on tc.colonist_id=c.id
WHERE tc.journey_id is NULL;

#04.Extract all travel cards
SELECT card_number, job_during_journey FROM travel_cards ORDER BY card_number;

#05. Extract all colonists
SELECT c.id,concat(c.first_name,' ', c.last_name) as 'full_name', c.ucn FROM colonists as c ORDER BY c.first_name, c.last_name,c.id;

#06.	Extract all military journeys
SELECT j.id, j.journey_start,j.journey_end FROM journeys as j
WHERE j.purpose = 'Military'
ORDER BY j.journey_start;

#07.	Extract all pilots
SELECT c.id, concat(c.first_name,' ', c.last_name) as 'full_name' FROM colonists as c 
JOIN travel_cards as tc on tc.colonist_id=c.id
WHERE tc.job_during_journey = 'Pilot'
ORDER BY c.id;

#08.	Count all colonists that are on technical journey
SELECT count(c.id) as 'count' FROM colonists as c
JOIN travel_cards as tc on tc.colonist_id=c.id
JOIN journeys as j on j.id= tc.journey_id
WHERE j.purpose = 'Technical';

#09.Extract the fastest spaceship
SELECT ss.`name` as 'spaceship_name',sp.`name` as 'spaceport_name' FROM spaceports as sp 
JOIN journeys as j on j.destination_spaceport_id= sp.id
JOIN spaceships as ss on ss.id = j.spaceship_id
ORDER BY ss.light_speed_rate DESC
LIMIT 1;

#10.Extract spaceships with pilots younger than 30 years
SELECT ss.`name`,ss.manufacturer FROM spaceships as ss
JOIN journeys as j on j.spaceship_id=ss.id 
JOIN travel_cards as tc on tc.journey_id=j.id
JOIN colonists as c on c.id= tc.colonist_id
WHERE 2019 - year(c.birth_date) < 30 AND tc.job_during_journey = 'Pilot'
ORDER BY ss.`name`;

#11. Extract all educational mission planets and spaceports
SELECT p.`name` as 'planet_name' ,sp.`name` as 'spaceport_name' from planets as p
JOIN spaceports as sp on sp.planet_id=p.id
JOIN journeys as j on j.destination_spaceport_id=sp.id
WHERE j.purpose= 'Educational'
ORDER BY sp.`name` DESC;

#12. Extract all planets and their journey count
SELECT p.`name`, count(j.id) as 'journeys_count' FROM planets as p
JOIN spaceports as sp on sp.planet_id=p.id
JOIN journeys as j on j.destination_spaceport_id=sp.id
GROUP BY p.`name`
ORDER BY journeys_count DESC,p.`name`;

#13. Extract the shortest journey
SELECT j.id, p.`name`, s.`name`, j.purpose
	FROM planets AS p
JOIN spaceports AS s ON p.id = s.planet_id
JOIN journeys AS j ON s.id = j.destination_spaceport_id
ORDER BY j.journey_end - j.journey_start
LIMIT 1;

#14.Extract the less popular job
SELECT tc.job_during_journey
	FROM travel_cards AS tc
JOIN journeys AS j ON tc.journey_id = j.id
GROUP BY j.id, tc.job_during_journey
ORDER BY j.journey_end - j.journey_start DESC, count(tc.id)
LIMIT 1;

#15. Get colonists count
DELIMITER $$
CREATE FUNCTION udf_count_colonists_by_destination_planet (planet_name VARCHAR (30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count_colonists INT;
    SET count_colonists:= (SELECT count(c.id)
		FROM colonists AS c
		JOIN travel_cards AS tc
		ON c.id = tc.colonist_id
		JOIN journeys AS j
		ON j.id = tc.journey_id
        JOIN spaceports AS s
        ON j.destination_spaceport_id = s.id
        JOIN planets AS p
        ON p.id = s.planet_id
        WHERE p.`name` = planet_name);
	RETURN count_colonists;
END $$

SELECT count(c.id)
		FROM colonists AS c
		JOIN travel_cards AS tc
		ON c.id = tc.colonist_id
		JOIN journeys AS j
		ON j.id = tc.journey_id
        JOIN spaceports AS s
        ON j.destination_spaceport_id = s.id
        JOIN planets AS p
        ON p.id = s.planet_id
        WHERE p.`name` = 'Otroyphus';


-- 16. Modify spaceship
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
    if (SELECT count(ss.name) FROM spaceships ss WHERE ss.name = spaceship_name > 0) THEN
        UPDATE spaceships ss
        SET ss.light_speed_rate = ss.light_speed_rate + light_speed_rate_increse
        WHERE name = spaceship_name;
    ELSE
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
    END IF;
END;
