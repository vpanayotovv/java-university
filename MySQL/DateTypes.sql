create database minions;

create table `minions`(
`id` int auto_increment primary key,
`name` varchar(45) not null,
`age` int null
);

create table `towns`(
`town_id` int auto_increment primary key,
`name` varchar(45) not null
);

alter table minions
  add column `town_id` int;

alter table minions
    add constraint fk_minions_towns
foreign key (`town_id`)
  references `towns`(`town_id`);

insert into `towns`(`town_id`,`name`)
value (1,'Sofia'),(2,'Plovdiv'),(3,'Varna');

insert into `minions`(id,name,age,town_id)
value (1,'Kevin',22,1),
      (2,'Bob',15,3),
      (3,'Steward',NULL,2);

truncate minions;

drop table minions;

drop table towns;

create database `soft_uni`;

create table `towns`(
    `id` int primary key auto_increment,
    `name` varchar(45)
);

create table `addresses`(
    `id` int primary key auto_increment,
    `addresses_text` varchar(45),
    `town_id` int,
    constraint fk_addresses_town
                        foreign key (`town_id`)
                        references `towns`(`id`)
);

create table `department`(
    `id` int primary key auto_increment,
    `name` varchar(45)
);

create table employees(
    `id` int primary key auto_increment,
    `first_name` varchar(30) not null ,
    `middle_name` varchar(30) not null ,
    `last_name` varchar(30) not null,
    `job_title` varchar(20) not null,
    `department_id` int not null,
    `hire_date` date,
    `salary` decimal(20,2),
    `address_id` int,
    constraint fk_employees_department
    foreign key(`department_id`)
    references `department`(`id`),
    constraint fk_employees_addresses
    foreign key(`address_id`)
    references `addresses`(`id`)
);

insert into towns
values
(1,'Sofia'),
(2,'Plovdiv'),
(3,'Varna'),
(4,'Burgas');

insert into department
values
(1,'Engineering'),
(2,'Sales'),
(3,'Marketing'),
(4,'Software Development'),
(5,'Quality Assurance');

insert into employees(id,first_name,middle_name,last_name,job_title,department_id,hire_date,salary)
values
(1,'Ivan','Ivanov','Ivanov','.NET Developer',4,'2013-02-01',3500.00),
(2,'Petar','Petrov','Petrov','Senior Engineer',1,'2004-03-02',4000.00),
(3,'Maria','Petrova','Ivanova','Intern',5,'2016-08-28',525.25),
(4,'Georgi','Terziev','Ivanov','CEO',2,'2007-12-09',3000.00),
(5,'Peter','Pan','Pan','Intern',3,'2016-08-28',599.88);

select * from towns
order by name;
select * from department
order by name;
select * from employees
order by salary desc;