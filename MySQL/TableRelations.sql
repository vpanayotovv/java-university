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
    manufacturer_id int auto_increment primary key,
    name varchar(50),
    established_on date
);

create table models(
    model_id int auto_increment primary key,
    name varchar(50),
    manufacturer_id int,

    constraint fk_models_manufacturers
    foreign key (manufacturer_id)
    references manufacturers(manufacturer_id)
);

insert into manufacturers(manufacturer_id, name, established_on)
values
('1','BMW','1916-03-01'),
('2','Tesla','2003-01-01'),
('3','Lada','1966-05-01');

insert into models (model_id, name, manufacturer_id)
values
       ('101','X1','1'),
       ('102','i6','1'),
       ('103','Model S','2'),
       ('104','Model X','2'),
       ('105','Model 3','2'),
       ('106','Nova','3');

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

create table cities(
  city_id int(11) auto_increment primary key,
  name varchar(50)
);

create table customers(
    customer_id int(11) auto_increment primary key,
    name varchar(50),
    birthday date,
    city_id int(11),

    constraint fk_customers_cities
    foreign key (city_id)
    references cities(city_id)
);

create table orders(
    order_id int(11) auto_increment primary key,
    customer_id int(11),
    constraint fk_orders_customers
    foreign key (customer_id)
    references customers(customer_id)
);

create table item_types(
    item_type_id int(11) auto_increment primary key,
    name varchar(50)
);

create table items(
    item_id int(11) auto_increment primary key,
    name varchar(50),
    item_type_id int (11),
    constraint fk_items_item_types
    foreign key (item_type_id)
    references item_types(item_type_id)
);

create table order_items(
    order_id int(11),
    item_id int(11),

    constraint pk_order_items
    primary key (order_id,item_id),

    constraint fk_order_items_items
    foreign key (item_id)
    references items(item_id),

    constraint fk_order_items_orders
    foreign key (order_id)
    references orders(order_id)
);