--liquibase formatted sql

--changeset tele_bot_music:1
create table account
(
    id            serial primary key,
    first_name    varchar(100),
    last_name     varchar(100),
    user_name     varchar(100),
    chat_id       integer,
    registered_at TIMESTAMP
);

--changeset tele_bot_music:2
create table music
(
    id   serial primary key,
    name varchar(199)
)