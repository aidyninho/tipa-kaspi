--liquibase formatted sql

--changeset aidyninho:1
create index transactions_account_from_id_idx on transactions(account_from_id);
create index transactions_status_idx on transactions(status);