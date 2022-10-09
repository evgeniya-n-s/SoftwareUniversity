#1.	 Mountains and Peaks
CREATE TABLE mountains (
id INT PRIMARY KEY auto_increment,
`name` varchar(50) not null
);

CREATE TABLE peaks(
id INT primary key auto_increment,
`name` varchar(50) not null,
mountain_id int,
constraint fk_peaks_mountains
foreign key (mountain_id)
references mountains(id)
);


#2.	 Trip Organization
select `driver_id`, `vehicle_type`, CONCAT_WS(' ', c.`first_name`, c.`last_name`) as 'driver_name'  
from `vehicles` as v
join `campers` as c 
on v.`driver_id` = c.`id`;

#3.	SoftUni Hiking
SELECT 
starting_point AS 'route_starting_point', 
end_point AS 'route_ending_point', 
leader_id, 
CONCAT(first_name,' ', last_name) as 'leader_name'
FROM routes
JOIN campers ON routes.leader_id = campers.id;

#4.	Delete Mountains
CREATE TABLE mountains (
id INT PRIMARY KEY auto_increment,
`name` varchar(50) not null
);

CREATE TABLE peaks(
id INT primary key auto_increment,
`name` varchar(50) not null,
mountain_id int,
constraint fk_peaks_mountains
foreign key (mountain_id)
references mountains(id)
ON DELETE CASCADE
);