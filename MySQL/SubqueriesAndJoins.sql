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
order by employee_id desc
limit 3;

select e.first_name,e.last_name,e.hire_date,d.name as dept_name
from employees e join departments d
on e.department_id = d.department_id
where date(e.hire_date) > '1999-01-01' and d.name in ('Sales','Finance')
order by hire_date;

select e.employee_id, e.first_name,p.name as project_name
from employees as e
join employees_projects as ep
on e.employee_id = ep.employee_id
join projects as p
on ep.project_id = p.project_id
where date(p.start_date) > '2002-08-13' and p.end_date is null
order by e.first_name , project_name
limit 5;

select e.employee_id,e.first_name,if(p.start_date >= '2005-01-01',null
    ,name ) as project_name
from employees as e
join employees_projects  as ep
on e.employee_id = ep.employee_id
join projects as p
on ep.project_id = p.project_id
where e.employee_id = 24
order by project_name;

select e.employee_id ,e.first_name,m.employee_id,m.first_name as manager_name
from employees as e
join employees as m
on e.manager_id = m.employee_id
where e.manager_id in (3,7)
order by first_name;

select e.employee_id ,concat(e.first_name,' ',e.last_name) as employee_name ,
      concat(m.first_name,' ',m.last_name) as manager_name , d.name
from employees e
join employees m
on e.manager_id= m.employee_id
join departments d
on e.department_id = d.department_id
order by e.employee_id
limit 5;

select avg(salary) as min_average_salary
from employees as e
join departments as d
on e.department_id = d.department_id
group by e.department_id
order by min_average_salary
limit 1;

use geography;

select c.country_code,m.mountain_range,p.peak_name,p.elevation
from countries as c
join mountains_countries as mc
on c.country_code = mc.country_code
join mountains as m
on mc.mountain_id = m.id
join peaks as p
on mc.mountain_id = p.mountain_id
where c.country_code  = 'BG'
order by p.elevation desc

select c.country_code,count(m.mountain_range) as mountain_range
from countries as c
join mountains_countries as mc
on c.country_code = mc.country_code
join mountains as m
on mc.mountain_id = m.id
where c.country_code in ('BG','RU','US')
group by c.country_code
order by mountain_range desc;
