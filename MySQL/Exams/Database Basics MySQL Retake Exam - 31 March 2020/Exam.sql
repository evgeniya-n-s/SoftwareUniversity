#1.
CREATE TABLE users(
id INT PRIMARY KEY,
username VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(30) NOT NULL,
email VARCHAR(50) NOT NULL,
gender CHAR(1) NOT NULL,
age INT NOT NULL,
job_title VARCHAR(40) NOT NULL,
ip VARCHAR(30) NOT NULL
);

CREATE TABLE addresses(
id INT PRIMARY KEY AUTO_INCREMENT,
address VARCHAR(30) NOT NULL,
town VARCHAR(30) NOT NULL,
country VARCHAR(30) NOT NULL,
user_id INT NOT NULL,
CONSTRAINT fk_addreses_users
FOREIGN KEY (user_id)
REFERENCES users(id)
);

CREATE TABLE photos(
id INT PRIMARY KEY AUTO_INCREMENT,
`description` TEXT NOT NULL,
`date` DATETIME NOT NULL,
views INT DEFAULT 0 NOT NULL
);

CREATE TABLE comments(
id INT PRIMARY KEY AUTO_INCREMENT,
`comment` VARCHAR(255) NOT NULL,
`date` DATETIME NOT NULL,
photo_id INT NOT NULL,
CONSTRAINT fk_comments_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

CREATE TABLE users_photos(
user_id INT NOT NULL,
photo_id INT NOT NULL,
CONSTRAINT fk_users
FOREIGN KEY (user_id)
REFERENCES users(id),
CONSTRAINT fk_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

CREATE TABLE likes(
id INT PRIMARY KEY AUTO_INCREMENT,
photo_id INT,
user_id INT,
CONSTRAINT fk_likes_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id),
CONSTRAINT fk_likes_users
FOREIGN KEY (user_id)
REFERENCES users(id)
);

#02.	Insert
INSERT INTO addresses(address,town,country,user_id)
SELECT (u.username),(u.`password`),(u.ip),(u.age) FROM users as u
WHERE u.gender LIKE 'M';

#03.	Update
UPDATE addresses
SET country = 
CASE
WHEN country LIKE 'B%' THEN 'Blocked'
WHEN country LIKE 'T%' THEN 'Test'
WHEN country LIKE 'P%' THEN 'In Progress'
ELSE country
END;

#04.	Delete
DELETE FROM addresses
WHERE id % 3 =0;

#05.	Users
SELECT u.username, u.gender, u.age FROM users as u ORDER BY u.age DESC, u.username;

#06.	Extract 5 Most Commented Photos
SELECT ph.id, ph.`date` as 'date_and_time', ph.`description`, count(c.`comment`) as 'commentsCount'
FROM photos as ph
JOIN comments as c on c.photo_id=ph.id
GROUP BY ph.id
ORDER BY commentsCount DESC, ph.id
LIMIT 5;

#07.	Lucky Users
SELECT concat(u.id,' ',u.username) as id_username, u.email FROM users as u
JOIN users_photos as up on up.user_id=u.id
JOIN photos as ph on ph.id=up.photo_id
WHERE u.id=ph.id
ORDER BY u.id;

#08.	Count Likes and Comments
SELECT p.id, 
	(SELECT count(id)
    	FROM likes
    	WHERE photo_id = p.id) AS likes_count, 
    	(SELECT count(id)
    	FROM comments
    	WHERE photo_id = p.id) AS comments_count
FROM photos AS p
ORDER BY likes_count DESC, comments_count DESC, p.id;

#09.	The Photo on the Tenth Day of the Month
SELECT concat(substring(ph.`description`,1,30),'...'), ph.`date` FROM photos as ph
WHERE day(ph.`date`) = 10
ORDER BY ph.`date` DESC;

#10.	Get User’s Photos Count
DELIMITER $$
CREATE FUNCTION udf_users_photos_count(username VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE number_of_photo INT;
    SET number_of_photo :=(SELECT count(up.photo_id)
		FROM users_photos AS up
		JOIN users AS u
		ON u.id = up.user_id
		WHERE u.username = username);
        RETURN number_of_photo;
END $$

#11.	Increase User Age
CREATE PROCEDURE udp_modify_user (address VARCHAR(30), town VARCHAR(30))
BEGIN
UPDATE users AS u
JOIN addresses as a on a.user_id=u.id
SET u.age = u.age + 10
WHERE a.address LIKE address and a.town LIKE town;

END$$
