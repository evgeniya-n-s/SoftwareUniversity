#1

CREATE TABLE countries(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) not NULL UNIQUE,
continent VARCHAR(30) NOT NULL,
currency VARCHAR(5) NOT NULL
);

create table genres(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE actors(
id INT(11) PRIMARY key AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL,
height INT(11),
awards INT(11),
country_id INT(11) NOT NULL
);


CREATE TABLE movies_additional_info(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
rating DECIMAL(10,2) NOT NULL,
runtime INT(11) NOT NULL,
picture_url VARCHAR(80) NOT NULL,
budget DECIMAL(10,2),
release_date DATE NOT NULL,
has_subtitles BOOLEAN,
`description` TEXT
);

CREATE TABLE movies(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(70) NOT NULL UNIQUE,
country_id INT(11) NOT NULL,
movie_info_id INT(11) NOT NULL UNIQUE
);


CREATE TABLE movies_actors(
movie_id INT(11),
actor_id INT(11)
);

CREATE TABLE genres_movies (
    genre_id INT(11),
    movie_id INT(11)
);

ALTER TABLE movies
add CONSTRAINT fk_movies_movies_info
FOREIGN KEY (movie_info_id)
REFERENCES movies_additional_info(id),
ADD CONSTRAINT fk_movies_countries
FOREIGN KEY (country_id)
REFERENCES countries(id);

ALTER TABLE actors
ADD CONSTRAINT fk_actors_countries
FOREIGN KEY (country_id)
REFERENCES countries(id);

ALTER TABLE movies_actors
ADD CONSTRAINT fk_movies_actors_movies
FOREIGN KEY (movie_id)
REFERENCES movies(id),
ADD CONSTRAINT fk_movies_actors_actors
FOREIGN KEY (actor_id)
REFERENCES actors(id);

ALTER TABLE genres_movies
ADD CONSTRAINT fk_genres_movies_movies
FOREIGN KEY (movie_id)
REFERENCES movies(id),
ADD CONSTRAINT fk_genres_movies_genres
FOREIGN KEY (genre_id)
REFERENCES genres(id);


#02.	Insert
INSERT INTO actors(
first_name, last_name, birthdate, height, awards, country_id)
SELECT (reverse(a.first_name)), (reverse(a.last_name)), (DATE(a.birthdate-2)), (a.height + 10),(a.country_id),(3) FROM actors as a
WHERE a.id <=10;

#03.	Update
UPDATE movies_additional_info AS m
SET m.runtime = m.runtime - 10
WHERE m.id >= 15 and m.id <=25;


#04.	Delete
DELETE c FROM countries c
LEFT JOIN movies AS m ON m.id = c.country_id
WHERE m.country_id is Null;

#05.	Countries
SELECT * FROM countries AS c
ORDER BY currency DESC,id;

#06.	Old movies
SELECT mai.id, m.title,mai.runtime, mai.budget, mai.release_date 
from movies_additional_info AS mai
JOIN movies as m
on m.movie_info_id = mai.id
WHERE (mai.release_date > '1995-12-31') AND (mai.release_date < '2000-01-01')
ORDER BY mai.runtime, mai.id
LIMIT 20;

#07.	Movie casting
SELECT 
	concat(a.first_name, ' ', a.last_name) AS 'full_name', 
    CONCAT(reverse(a.last_name),length(a.last_name),'@cast.com') AS 'email', 
    concat(2022 - Year(a.birthdate)) AS 'age', 
    a.height
FROM actors AS a
LEFT JOIN movies_actors As ma ON ma.actor_id = a.id
WHERE ma.movie_id is NULL
ORDER BY a.height;


#08.	International festival
SELECT c.`name`, count(m.title) AS 'movies_count'
from countries AS c
JOIN movies AS m
ON m.country_id = c.id
GROUP BY c.`name`
HAVING `movies_count` >=7
ORDER BY c.`name` DESC;

#09.	Rating system
SELECT 
	m.title, 
	CASE
		WHEN mai.rating <= 4 THEN 'poor'
		WHEN mai.rating <= 7 THEN 'good'
		ELSE 'excellent'
	END as 'rating',
	IF(mai.has_subtitles,'english','-')  AS 'subtitles',
	mai.budget
FROM movies AS m
JOIN movies_additional_info AS mai on mai.id = m.id
ORDER BY mai.budget DESC;


#10.	History movies
DELIMITER $$
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count_movies INT;
    SET count_movies:=(
    SELECT COUNT(g.`name`) movies
	FROM actors AS a
	JOIN movies_actors as ma on ma.actor_id = a.id
	JOIN genres_movies as gm on gm.movie_id = ma.movie_id
	JOIN genres as g on g.id = gm.genre_id
	WHERE concat(a.first_name,' ',a.last_name) = full_name and g.`name` = 'History'
	GROUP BY g.name);
RETURN count_movies;
END $$

#11.	Movie awards
CREATE PROCEDURE udp_award_movie(movie_title VARCHAR(50))
BEGIN
UPDATE actors a
        JOIN movies_actors ma on a.id = ma.actor_id
        JOIN movies m on m.id = ma.movie_id
    SET  a.awards = a.awards + 1
    WHERE m.title = movie_title;
END$$





