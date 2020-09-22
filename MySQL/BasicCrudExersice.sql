select *
from departments
order by department_id;

select name
from departments
order by department_id;

select first_name,last_name,salary
from employees
order by employee_id;

select first_name,middle_name,last_name
from employees
order by employee_id;

select concat(first_name, '.', last_name, '@softuni.bg') as full_email_address
from employees;
