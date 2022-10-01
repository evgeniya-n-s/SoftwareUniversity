#12.	Games from 2011 and 2012 Year
SELECT name, date_format(start,'%Y-%m-%d') AS start 
FROM games 
WHERE YEAR(start) IN (2011,2012)
ORDER BY start, name 
LIMIT 50;

#13. User Email Providers
SELECT user_name, SUBSTRING(email, locate('@',email)+1) AS email_provider 
FROM users 
ORDER BY email_provider, user_name;

#14.	 Get Users with IP Address Like Pattern
SELECT user_name, ip_address 
FROM users 
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

#15.	 Show All Games with Duration and Part of the Day
SELECT name AS game, 
CASE
WHEN hour(start) < 12 THEN 'Morning'
WHEN hour(start) < 18 THEN 'Afternoon'
ELSE 'Evening'
END
AS `Part of the Day`, 
CASE
WHEN duration <= 3 THEN 'Extra Short'
WHEN duration <= 6 THEN 'Short'
WHEN duration <= 10 THEN 'Long'
ELSE 'Extra Long'
END
duration FROM games;