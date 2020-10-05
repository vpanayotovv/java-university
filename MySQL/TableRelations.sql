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
    model_id int primary key ,
    name varchar(10) unique,
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

create table exams(
    exam_id int primary key,
    name varchar(30)
);

create table students(
    student_id int primary key,
    name varchar(30)
);

create table students_exams(
    student_id int,
    exam_id int,

    constraint pk_students_exams
    primary key (student_id,exam_id),

    constraint fk_students_exams_students
    foreign key students_exams(student_id)
    references students(student_id),

    constraint fk_students_exams_exams
    foreign key students_exams(exam_id)
    references exams(exam_id)
);

insert into exams(exam_id, name)
values
('101','Spring MVC'),
('102','Neo4j'),
('103','Oracle 11g');

insert into students(student_id, name)
values
('1','Mila'),
('2','Toni'),
('3','Ron');

insert into students_exams(student_id, exam_id)
values
('1','101'),
('1','102'),
('2','101'),
('3','103'),
('2','102'),
('2','103');