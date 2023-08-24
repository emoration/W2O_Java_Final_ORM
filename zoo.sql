create table zoo
(
    id    int auto_increment
        primary key,
    name  varchar(30) null,
    price double      null,
    constraint zoo_uk
        unique (id)
);