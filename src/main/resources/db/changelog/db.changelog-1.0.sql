--liquibase formatted sql

--changeset aidyninho:1
create table if not exists users
(
    phone    varchar(12) primary key,
    password varchar(255) not null,
    role     varchar(255) not null
);

create table limits
(
    id                  serial primary key,
    limit_sum           numeric    default 1000.0,
    current_sum         numeric    default 0.0,
    currency_short_name varchar(3) default 'USD',
    limit_datetime      timestamptz
);

create table if not exists currencies
(
    currency_from varchar(3),
    currency_to   varchar(3),
    rate          numeric     not null,
    datetime      timestamptz not null,
    primary key (currency_from, currency_to)
);

create table if not exists accounts
(
    id                  varchar(10) primary key,
    currency_short_name varchar(3),
    balance             numeric default 0.0,
    user_phone          varchar(12) references users (phone),
    limit_id            int references limits (id)
);

create table if not exists transactions
(
    id               bigserial primary key,
    account_from     varchar(10) references accounts (id),
    account_to       varchar(10) references accounts (id),
    sum              numeric      not null,
    expense_category varchar(255) not null,
    datetime         timestamptz  not null,
    status           varchar(255)
);