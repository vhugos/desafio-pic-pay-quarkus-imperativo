CREATE USER admin_user WITH PASSWORD 'passwd';


CREATE DATABASE picpay1;
\connect picpay1;

CREATE SCHEMA admin_sch;


GRANT CONNECT ON DATABASE picpay1 TO admin_user;
GRANT ALL PRIVILEGES ON SCHEMA admin_sch TO admin_user;