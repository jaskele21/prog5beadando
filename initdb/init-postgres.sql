CREATE ROLE beadando WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	REPLICATION
	CONNECTION LIMIT -1
	PASSWORD '12345678';

CREATE TABLE customers (
id serial PRIMARY KEY,
name VARCHAR(100) NOT NULL,
phonenumber VARCHAR(100) NOT NULL
);

CREATE TABLE tyres (
id serial PRIMARY KEY,
customer_id serial NOT NULL REFERENCES customers(id),
brand VARCHAR(100) NOT NULL,
war VARCHAR(100) NOT NULL,
quantity smallint NOT NULL
);

CREATE TABLE users (
id serial PRIMARY KEY,
username VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL
);

CREATE TABLE role (
id serial PRIMARY KEY,
rolename VARCHAR(100)
);

CREATE TABLE user_role (
id serial PRIMARY KEY,
user_id serial NOT NULL REFERENCES users(id),
role_id serial NOT NULL REFERENCES role(id)
);

INSERT INTO users (username, password) values ('admin', '$2a$10$prNhwDKXkPAzTHfHtShojeofzSswq1lsQHkNYG3ZeFIYlZi50P5B2');
INSERT INTO users (username, password) values ('user', '$2a$10$prNhwDKXkPAzTHfHtShojeofzSswq1lsQHkNYG3ZeFIYlZi50P5B2');

INSERT INTO role (rolename) values('USER');
INSERT INTO role (rolename) values('ADMIN');

INSERT INTO user_role (user_id, role_id) select * from (select u.id from users u where u.username = 'admin') a, (select r.id from "role" r where r.rolename = 'ADMIN') b;
INSERT INTO user_role (user_id, role_id) select * from (select u.id from users u where u.username = 'admin') a, (select r.id from "role" r where r.rolename = 'USER') b;
INSERT INTO user_role (user_id, role_id) select * from (select u.id from users u where u.username = 'user') a, (select r.id from "role" r where r.rolename = 'USER') b;