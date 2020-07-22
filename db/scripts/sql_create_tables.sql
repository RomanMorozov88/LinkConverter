create table sites
(
    login    varchar primary key,
    name     varchar unique,
    password varchar
);

create table pages
(
    id            serial primary key,
    parent_site   varchar,
    original_url  varchar unique,
    converted_url varchar unique,
    count         integer not null,
    foreign key (parent_site) references sites (login)
);