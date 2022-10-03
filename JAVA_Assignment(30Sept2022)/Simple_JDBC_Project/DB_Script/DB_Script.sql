 
create database sampleDB;

use sampleDB;

create table Person 
(id bigint primary key, 
name varchar(30) not null,
mobile varchar(10) not null,
email varchar(30) not null);

desc Person;
