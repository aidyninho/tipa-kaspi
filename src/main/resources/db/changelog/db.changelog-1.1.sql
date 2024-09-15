--liquibase formatted sql

--changeset aidyninho:1
insert into users (phone, password, role)
values ('test', '$2a$12$H4p4X49r5F9/2DQvIELVf.12VezuwA4ODFU.wscf5Z9INaoKKwf1C', 'USER');