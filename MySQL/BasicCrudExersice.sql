select *
from departments
order by department_id;

select name
from departments
order by department_id;

select first_name, last_name, salary
from employees
order by employee_id;

select first_name, middle_name, last_name
from employees
order by employee_id;

select concat(first_name, '.', last_name, '@softuni.bg') as full_email_address
from employees;

create view full_email_address as
    select concat(first_name,'.',last_name,'@softuni.bg') as full_email_address
from employees;

select *
from full_email_address;

select distinct salary
from employees
order by employee_id;

select *
from employees
where job_title = 'Sales Representative';

select first_name,last_name,job_title
from employees
where salary between 20000 and 30000;

select concat(first_name,' ',middle_name,' ',last_name) as "Full Name"
from employees
where salary = 25000 or salary = 14000 or salary = 12500 or salary = 23600;

select first_name,last_name
from employees
where manager_id is null;