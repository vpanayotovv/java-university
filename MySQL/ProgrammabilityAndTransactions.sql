create function ufn_count_employees_by_town(town_name varchar(50))
returns int deterministic
begin
    declare emp_count int;
    set emp_count := (select count(*)
        from employees
        join addresses using (address_id)
        join towns using(town_id)
        where name = town_name);

    return emp_count;
end;

select ufn_count_employees_by_town('Sofia') as count;


create procedure ups_select_employees_by_seniority(years_working int)
begin
    select employee_id,concat(first_name,' ',last_name) as full_name , hire_date
        from employees
            where round(datediff(now(),date(hire_date)) / 365.25 ,0) < years_working;
end;

drop procedure ups_select_employees_by_seniority;

CALL ups_select_employees_by_seniority(18);

create procedure usp_raise_salaries(department_name varchar(50))
begin
    update employees as e join departments as d using (department_id)
    set salary = salary * 1.05
    where d.name = department_name;
end;

call usp_raise_salaries('Sales');

create procedure usp_raise_salary_by_id(id int)
begin
    update employees as e
set salary = salary * 1.05
where employee_id = id;
end;

call usp_raise_salary_by_id(1);

select employee_id,first_name,salary
from employees;