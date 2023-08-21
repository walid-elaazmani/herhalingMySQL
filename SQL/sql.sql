use accountapp;

create table User(
id int PRIMARY KEY,
fname varchar(50),
lname varchar(50),
accEmail varchar(50),

FOREIGN KEY (accEmail) REFERENCES Account(email)
);

show tables;
describe User;

create table Account(
email varchar(50) PRIMARY KEY,
passw varchar(50) NOT NULL
);

select User;