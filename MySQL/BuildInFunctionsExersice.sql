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
where job_title not like '&engineer%'
order by employee_id;

select `name`
from towns
where char_length(name) in (5,6)
order by name;