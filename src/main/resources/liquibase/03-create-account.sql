create table account(
    id int(6) not null auto_increment,
    username varchar(255) unique,
    password varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    primary key(id)
);