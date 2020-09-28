use gringotts;

select count(*)
from wizzard_deposits;

select max(magic_wand_size)
from wizzard_deposits;

select deposit_group, max(magic_wand_size) as longest_magic_wand
from wizzard_deposits
group by deposit_group
order by longest_magic_wand, deposit_group;

create view Low as
select deposit_group, avg(magic_wand_size) as Lowest
from wizzard_deposits
group by deposit_group;

select deposit_group
from Low
limit 1;

select deposit_group
from wizzard_deposits
group by deposit_group
having avg(magic_wand_size)
limit 1;

select deposit_group, sum(deposit_amount) as total_sum
from wizzard_deposits
group by deposit_group
order by total_sum;

select deposit_group, sum(deposit_amount) as total_sum
from wizzard_deposits
where magic_wand_creator = 'Ollivander family'
group by deposit_group
order by deposit_group;

select deposit_group, sum(deposit_amount) as total_sum
from wizzard_deposits
where magic_wand_creator = 'Ollivander family'
group by deposit_group
having total_sum < 150000
order by total_sum desc;

select deposit_group, magic_wand_creator, min(deposit_charge) as min_deposit_charge
from wizzard_deposits
group by deposit_group, magic_wand_creator
order by magic_wand_creator, deposit_group;

select case
           when age between 0 and 10 then '[0-10]'
           when age between 11 and 20 then '[11-20]'
           when age between 21 and 30 then '[21-30]'
           when age between 31 and 40 then '[31-40]'
           when age between 41 and 50 then '[41-50]'
           when age between 51 and 60 then '[51-60]'
           else '[61+]'
           end as 'age_group',
       count(*) as wizard_count
from wizzard_deposits
group by age_group
order by age_group;

select distinct LEFT(first_name,1) as first_letter
from wizzard_deposits
where deposit_group = 'Troll Chest'
order by first_letter;

select deposit_group ,
       IF(is_deposit_expired is false, '0', '1') as is_deposit_expired,avg(deposit_interest) as average_intrest
from wizzard_deposits
where deposit_start_date > '1985-01-01'
group by deposit_group,is_deposit_expired
order by deposit_group desc , is_deposit_expired;