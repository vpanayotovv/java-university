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

