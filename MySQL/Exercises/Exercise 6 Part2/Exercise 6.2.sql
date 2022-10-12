#12.	Highest Peaks in Bulgaria
SELECT c.country_code, m.mountain_range, p.peak_name, p.elevation 
FROM countries AS c
JOIN mountains_countries as mc
ON c.country_code = mc.country_code
JOIN mountains as m
ON m.id = mc.mountain_id
JOIN peaks as p
ON p.mountain_id = m.id
WHERE c.country_code LIKE 'BG' AND p.elevation > 2835
ORDER BY p.elevation DESC;

#13.	Count Mountain Ranges
SELECT mc.country_code, COUNT(m.mountain_range)
FROM mountains AS m
JOIN mountains_countries as mc
ON m.id = mc.mountain_id
WHERE mc.country_code IN('BG', 'US', 'RU')
GROUP BY mc.country_code
ORDER BY m.mountain_range DESC;

#14.	Countries with Rivers
SELECT c.country_name, r.river_name
FROM countries AS c
LEFT JOIN countries_rivers as cr
ON c.country_code = cr.country_code
LEFT JOIN rivers as r
ON r.id = cr.river_id
WHERE c.continent_code LIKE 'AF'
ORDER BY c.country_name
LIMIT 5;

#15.	*Continents and Currencies


#16.  Countries Without Any Mountains
SELECT COUNT(c.country_code) 
FROM countries AS c
LEFT JOIN mountains_countries AS mc
ON c.countrY_code = mc.country_code
LEFT JOIN mountains AS m
ON m.id = mc.mountain_id
WHERE m.id is NULL;



#17.  Highest Peak and Longest River by Country
 SELECT c.country_name, MAX(p.elevation) AS 'highest_peak_elevation', MAX(r.length) AS 'longest_river_length'
 from countries AS c
 LEFT JOIN mountains_countries AS mc
 on c.country_code = mc.country_code
 LEFT JOIN peaks AS p
 on p.mountain_id = mc.mountain_id
 LEFT JOIN countries_rivers AS cr
 on cr.country_code = c.country_code
 LEFT JOIN rivers AS r
 on r.id = cr.river_id
 GROUP BY c.country_name
 ORDER BY `highest_peak_elevation` DESC , `longest_river_length` DESC , c.`country_name`
 LIMIT 5;