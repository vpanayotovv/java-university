create table people
(
    person_id   int primary key auto_increment not null,
    first_name  varchar(10)                    not null,
    salary      decimal(10, 2)                 not null,
    passport_id int unique                     not null
);
insert into people (first_name, salary, passport_id)
values ('Roberto', '43300.00', '102'),
       ('Tom', '56100.00', '103'),
       ('Yana', '60200.00', '101');

create table passports
(
    passport_id     int primary key not null,
    passport_number varchar(50) unique not null
);
insert into passports(passport_id, passport_number)
values ('101', 'N34FG21B'),
       ('102', 'K65LO4R7'),
       ('103', 'ZE657QP2');

alter table people
add constraint fk_persons_passports
foreign key people(passport_id)
references passports(passport_id);

create table manufacturers(
    manufacturer_id int primary key auto_increment unique,
    name varchar(10),
    established_on date
);

insert into manufacturers(manufacturer_id, name, established_on)
values
('1','BMW','1916-03-01'),
('2','Tesla','2003-01-01'),
('3','Lada','1966-05-01');

create table models(
    model_id int unique,
    name varchar(10),
    manufacturer_id int
);

insert into models (model_id, name, manufacturer_id)
values
       ('101','X1','1'),
       ('102','i6','1'),
       ('103','Model S','2'),
       ('104','Model X','2'),
       ('105','Model 3','2'),
       ('106','Nova','3');

alter table models
add constraint fk_models_manufacturers
foreign key models(manufacturer_id)
references manufacturers(manufacturer_id);