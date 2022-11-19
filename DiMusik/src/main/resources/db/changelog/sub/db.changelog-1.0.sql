--liquibase formatted sql

--changeset tele_bot_music:1
create table message_steps
(
    id   serial primary key,
    step varchar(30)
);

--changeset tele_bot_music:2
insert into message_steps(step)
values ('neutral');
insert into message_steps(step)
values ('request to enter a song');

--changeset tele_bot_music:3
create table account
(
    id            serial primary key,
    first_name    varchar(100),
    last_name     varchar(100),
    user_name     varchar(100),
    chat_id       integer,
    registered_at TIMESTAMP,
    step_id       integer,
    foreign key (step_id) references message_steps (id)
);

--changeset tele_bot_music:4
create table music
(
    id         serial primary key,
    name       varchar(199),
    account_id integer,
    foreign key (account_id) references account (id)
);

