use gringotts;

select count(*)
from wizzard_deposits;

select max(magic_wand_size)
from wizzard_deposits;

select deposit_group, max(magic_wand_size) as longest_magic_wand
from wizzard_deposits
group by deposit_group
order by longest_magic_wand,deposit_group;

create view Low as
select deposit_group , avg(magic_wand_size)as Lowest
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

select deposit_group,sum(deposit_amount) as total_sum
from wizzard_deposits
group by deposit_group
order by total_sum;

select deposit_group,sum(deposit_amount) as total_sum
from wizzard_deposits
where magic_wand_creator = 'Ollivander family'
group by deposit_group
order by deposit_group;

select deposit_group,sum(deposit_amount) as total_sum
from wizzard_deposits
where magic_wand_creator = 'Ollivander family'
group by deposit_group
having total_sum < 150000
order by total_sum desc;

select deposit_group,magic_wand_creator,min(deposit_charge) as min_deposit_charge
from wizzard_deposits
group by deposit_group, magic_wand_creator
order by magic_wand_creator,deposit_group;



