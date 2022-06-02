-- DDL create, drop, alter, comment, rename

--initialize db by deleting tables
drop table if exists users cascade;
drop table if exists comments cascade;
drop table if exists threads cascade;

--create users table
CREATE TABLE users (	
id varchar not null,
name varchar not null,
password varchar not null,

-- constraint requires that the primary key be the 'id' column
constraint pk_users_id
	primary key (id)
);



--create threads table
CREATE TABLE threads (	
id varchar not null,
title varchar not null,
user_id varchar not null,

-- constraint requires that the primary key be the 'id' column
constraint pk_threads_id
	primary key (id),
--constraint requires that user_id reference users.id
constraint fk_users_id
	foreign key (user_id) references users (id)

);

--create comments table
CREATE TABLE comments (	
id varchar not null,
thread_id varchar not null,
user_id varchar not null,
message varchar not null,

-- constraint requires that the primary key be the 'id' column
constraint pk_comments_id
	primary key (id),
--constraint requires that user_id reference users.id
constraint fk_threads_id
	foreign key (thread_id) references threads (id),
constraint fk_users_id
	foreign key (user_id) references users (id)	
);
