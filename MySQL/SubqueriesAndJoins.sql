use soft_uni;

select employee_id,concat(first_name,' ', last_name) as full_name,
       d.department_id , name as department_name
from departments as d join employees as e
on d.manager_id = e.employee_id
order by e.employee_id
limit 5;

select t.town_id, t.name as town_name , a.address_text
from towns as t join addresses as a
on t.town_id = a.town_id
where a.town_id in ('32','9','15')
order by town_id , address_id;

select COUNT(*)
from employees
where salary > (
    select avg(salary)
    from employees
    );

select employee_id,first_name,last_name,department_id,salary
from employees
where manager_id IS null;

select e.employee_id , e.job_title,a.address_id,a.address_text
from employees as e join addresses as a
on e.address_id = a.address_id
order by address_id
limit 5;

select first_name,last_name,name as town,address_text
from employees as e join towns as t join addresses as a
on e.address_id = a.address_id and t.town_id = a.town_id
order by first_name,last_name
limit 5;

select e.employee_id,e.first_name,e.last_name,d.name
from employees as e join departments as d
on e.department_id = d.department_id
where d.name = 'Sales'
order by employee_id desc;

select e.employee_id,e.first_name,e.salary,d.name
from employees as e join departments as d
on e.department_id = d.department_id
where salary > '15000'
order by d.department_id desc
limit 5;

select e.employee_id , e.first_name
from employees as e left join employees_projects as p
on e.employee_id = p.employee_id
where project_id is null
order by employee_id desc;