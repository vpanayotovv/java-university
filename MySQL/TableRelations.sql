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

use geography;
