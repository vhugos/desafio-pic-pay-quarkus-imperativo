-- liquibase formatted sql
-- changeset picpay:1
CREATE TABLE USERS(
    ID SERIAL PRIMARY KEY,
    FULLNAME TEXT  NOT NULL,
    CPF TEXT UNIQUE NOT NULL,
    EMAIL TEXT UNIQUE NOT NULL,
    PASSWORD TEXT NOT NULL,
    USER_TYPE INT,
    BALANCE numeric(38,2)
);


alter table if exists USERS alter column id set data type bigint;
create sequence users_SEQ start with 1 increment by 50;

CREATE TABLE TRANSACTIONS(
    ID SERIAL PRIMARY KEY,
    AMOUNT numeric(38,2) NOT NULL,
    PAYER_ID BIGINT REFERENCES USERS(ID),
    PAYEE_ID BIGINT  REFERENCES USERS(ID),
    TRANSACTION_DATE TIMESTAMP WITH TIME ZONE DEFAULT now()
);


alter table if exists TRANSACTIONS alter column id set data type bigint;
create sequence transactions_SEQ start with 1 increment by 50;