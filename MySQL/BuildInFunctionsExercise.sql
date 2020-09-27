use soft_uni;

select first_name,last_name
from employees
where first_name LIKE 'Sa%'
order by employee_id;

select first_name, last_name
from employees
where last_name like '%ei%'
order by employee_id;

select first_name
from employees
where department_id in (3,10)
AND year(hire_date) between 1995 and 2005
order by employee_id;

select first_name,last_name
from employees
where job_title not like '%engineer%'
order by employee_id;

select `name`
from towns
where char_length(name) in (5,6)
order by name;

select name
from towns
where substring(name,1,1) in ('m','k','b','e')
order by name;

select name
from towns
where substring(name,1,1) not in ('r','b','d')
order by name;

create view v_employees_hired_after_2000 as
    select first_name,last_name
from employees
where year(hire_date) > 2000;

select *
from v_employees_hired_after_2000;

select first_name,last_name
from employees
where char_length(last_name) = 5;

use geography;

select country_name,iso_code
from countries
where country_name like '%a%a%a%'
order by iso_code;

select peak_name,river_name,
lower(concat(peak_name,substring(river_name,2))) as mix
from rivers,peaks
where right(peak_name,1) = left(river_name,1)
order by mix;

