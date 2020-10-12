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