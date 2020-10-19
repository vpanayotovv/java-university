create schema instd;

use instd;

create table users
(
    id        int primary key,
    username  varchar(30) unique not null,
    password  varchar(30)        not null,
    email     varchar(50)        not null,
    gender    char(1)            not null,
    age       int                not null,
    job_title varchar(40)        not null,
    ip        varchar(30)        not null
);


create table addresses
(
    id      int primary key auto_increment,
    address varchar(30) not null,
    town    varchar(30) not null,
    country varchar(30) not null,
    user_id int         not null,
    constraint fk_addresses_users
        foreign key (user_id)
            references users (id)
);

create table photos
(
    id          int primary key auto_increment,
    description text          not null,
    date        datetime      not null,
    views       int default 0 not null
);

create table comments
(
    id       int primary key auto_increment,
    comment  varchar(255) not null,
    date     datetime     not null,
    photo_id int          not null,
    constraint fk_comments_photos
        foreign key (photo_id)
            references photos (id)
);

create table users_photos
(
    user_id  int not null,
    photo_id int not null,
    constraint fk_users_photos_users
        foreign key (user_id)
            references users (id),
    constraint fk_users_photos_photos
        foreign key (photo_id)
            references photos (id)
);

create table likes
(
    id       int primary key auto_increment,
    photo_id int,
    user_id  int,
    constraint fk_likes_photos
        foreign key (photo_id)
            references photos (id),
    constraint fk_likes_users
        foreign key (user_id)
            references users (id)
);

insert into addresses(address, town, country, user_id)
select username, password, ip, age
from users
where gender = 'M';

update addresses
set country = (
    case
        when country like 'B%' then 'Blocked'
        when country like 'T%' then 'Test'
        when country like 'P%' then 'In Progress'
        end
    )
where left(country, 1) in ('B', 'T', 'P');

delete
from addresses
where id % 3 = 0;

select username, gender, age
from users
order by age desc, username;

select p.id, p.date, p.description ,count(photo_id) as commentsCount
from photos as p
         join comments c
              on p.id = c.photo_id
group by photo_id
order by commentsCount desc , photo_id
limit 5;

select concat(u.id,' ',u.username) as id_username,email
from users as u
    join users_photos as up
        on u.id = up.user_id
where up.photo_id = up.user_id
order by u.id;

select p.id,count(distinct l.id) as likes_count,count(distinct c.id) comments_count
from photos as p
left join likes l on p.id = l.photo_id
left join comments c on p.id = c.photo_id
group by p.id
order by likes_count desc,comments_count desc , p.id;

