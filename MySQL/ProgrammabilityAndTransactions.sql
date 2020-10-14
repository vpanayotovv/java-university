create function ufn_count_employees_by_town(town_name varchar(50))
    returns int deterministic
begin
    declare emp_count int;
    set emp_count := (select count(*)
                      from employees
                               join addresses using (address_id)
                               join towns using (town_id)
                      where name = town_name);

    return emp_count;
end;

select ufn_count_employees_by_town('Sofia') as count;


create procedure ups_select_employees_by_seniority(years_working int)
begin
    select employee_id, concat(first_name, ' ', last_name) as full_name, hire_date
    from employees
    where round(datediff(now(), date(hire_date)) / 365.25, 0) < years_working;
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

create procedure usp_raise_salary_by_id(id int)
begin
    start transaction;
    if ((select count(*) from employees where employee_id = id) = 0)
    then
        rollback;
    else
        update employees as e
        set salary = salary * 1.05
        where employee_id = id;
        commit;
    end if;
end;

/* Triggers */

create table deleted_employees
(
    employee_id   int primary key,
    first_name    varchar(50)    not null,
    last_name     varchar(50)    not null,
    middle_name   varchar(50)    null,
    job_title     varchar(50)    not null,
    department_id int            not null,
    salary        decimal(19, 4) not null
);

create trigger tr_deleted_employees
    after delete
    on employees
    for each row
    begin
        insert into deleted_employees(employee_id, first_name, last_name, middle_name, job_title, department_id, salary)
        values (old.employee_id,old.first_name,old.last_name,old.middle_name,old.job_title,old.department_id,old.salary);
    end;

delete from employees
where employee_id > 200;

