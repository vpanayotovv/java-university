create table countries
(
    id   int primary key auto_increment,
    name varchar(45) not null
);

create table towns
(
    id         int primary key auto_increment,
    name       varchar(45) not null,
    country_id int         not null,

    constraint fk_towns_countries
        foreign key (country_id)
            references countries (id)
);

create table stadiums
(
    id       int primary key auto_increment,
    name     varchar(45) not null,
    capacity int         not null,
    town_id  int         not null,

    constraint fk_stadiums_towns
        foreign key (town_id)
            references towns (id)
);

create table teams
(
    id          int primary key auto_increment,
    name        varchar(45)          not null,
    established date                 not null,
    fan_base    bigint(20) default 0 not null,
    stadium_id  int                  not null,

    constraint fk_teams_stadiums
        foreign key (stadium_id)
            references stadiums (id)
);

create table skills_data
(
    id        int primary key auto_increment,
    dribbling int,
    pace      int,
    passing   int,
    shooting  int,
    speed     int,
    strength  int
);

create table coaches
(
    id          int primary key auto_increment,
    first_name  varchar(10)              not null,
    last_name   varchar(20)              not null,
    salary      decimal(10, 2) default 0 not null,
    coach_level int            default 0 not null
);

create table players
(
    id             int primary key auto_increment,
    first_name     varchar(10)              not null,
    last_name      varchar(20)              not null,
    age            int            default 0 not null,
    position       char(1)                  not null,
    salary         decimal(10, 2) default 0 not null,
    hire_date      datetime,
    skills_data_id int                      not null,
    team_id        int,

    constraint fk_players_skills_date
        foreign key (skills_data_id)
            references skills_data (id),

    constraint fk_players_teams
        foreign key (team_id)
            references teams (id)
);

create table players_coaches
(
    player_id int,
    coach_id  int,

    constraint pk_players_coaches
        primary key (player_id, coach_id),

    constraint fk_players_coaches_players
        foreign key (player_id)
            references players (id),

    constraint fk_players_coaches_coaches
        foreign key (coach_id)
            references coaches (id)
);

insert into coaches(first_name, last_name, salary, coach_level)
select first_name, last_name, salary, char_length(first_name)
from players
where age >= 45;

update coaches as c
    join players_coaches pc
    on c.id = pc.coach_id
set coach_level = coach_level + 1
where c.id = pc.coach_id
  and left(c.first_name, 1) = 'A';

delete
from players
where age >= 45;


select first_name, age, salary
from players
order by salary desc;

select p.id, concat(first_name, ' ', last_name) as full_name, age, position, hire_date
from players as p
         join skills_data sd on sd.id = p.skills_data_id
where p.age < 23
  and position = 'A'
  and hire_date is null
  and sd.strength > 50
order by p.salary, age;

SELECT t.name, t.established, t.fan_base, count(p.id) as players_count
from teams as t
         left join players p
                   on t.id = p.team_id
group by t.id, t.fan_base
order by players_count desc, t.fan_base desc;

select max(sd.speed) as speed, t.name
from towns as t
         left join stadiums as s
                   on t.id = s.town_id
         left join teams as te
                   on s.id = te.stadium_id
         left join players as p
                   on te.id = p.team_id
         left join skills_data as sd
                   on p.skills_data_id = sd.id
where te.name != 'Devify'
group by t.name
order by speed desc, t.name;

select c.name , count(p.id) as total_count_of_players , sum(salary) as total_sum_of_salaries
from countries as c
         left join towns t
                   on c.id = t.country_id
         left join stadiums s
                   on t.id = s.town_id
         left join teams as te
                   on s.id = te.stadium_id
        left join players p on te.id = p.team_id
group by c.name
order by total_count_of_players desc ,c.name;

create function udf_stadium_players_count(stadium_name varchar(30))
returns int deterministic
begin
    declare result int;
    set result := (
        select count(p.id)
from stadiums as s
left join countries c
on s.name = c.name
left join teams t
on s.id = t.stadium_id
left join players p on t.id = p.team_id
where s.name = stadium_name
group by s.name
        );
    return result;
end;