drop table if exists authorities cascade;
drop table if exists users cascade;
drop table if exists post cascade;
drop table if exists comments;

CREATE TABLE IF NOT EXISTS authorities (
   id serial primary key,
   authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE IF NOT EXISTS users (
   id serial primary key,
   username VARCHAR(50) NOT NULL unique,
   password VARCHAR(100) NOT NULL,
   enabled boolean default true,
   authority_id int not null references authorities(id)
);

create table IF NOT EXISTS posts (
   id serial primary key,
   name varchar(2000),
   description text,
   user_id int references users (id) not null,
   created timestamp without time zone not null default now()
);

create table IF NOT EXISTS comments (
   id serial primary key,
   description varchar (2000),
   created timestamp without time zone not null default now(),
   post_id int not null references posts(id),
   user_id int not null references users(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('admin', true, '$2a$10$KusNHfQxA663wnHkGIr2IeY7Dzz5ORnDHBGAJr6JpEMEdNuawp/9O',
        (select id from authorities where authority = 'ROLE_ADMIN'));

insert into posts (name, user_id) values ('О чем этот форум?', 1);
insert into posts (name, user_id) values ('Правила форума.', 1);
