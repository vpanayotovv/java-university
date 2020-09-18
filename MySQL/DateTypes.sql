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

