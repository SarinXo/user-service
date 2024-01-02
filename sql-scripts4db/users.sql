CREATE SCHEMA IF NOT EXISTS users_schema;

CREATE TABLE IF NOT EXISTS users_schema.users(
    farmer_id   SERIAL PRIMARY KEY,
    login       VARCHAR(20) UNIQUE,
    password    VARCHAR(40),
    role        VARCHAR(20)
);

INSERT INTO users_schema.users (login, password, role)
    VALUES
('user1', 'password1', 'USER'),
('user2', 'password2', 'USER'),
('user3', 'password3', 'USER'),
('user4', 'password4', 'USER'),
('user5', 'password5', 'USER'),
('user6', 'password6', 'USER'),
('user7', 'password7', 'USER'),
('user8', 'password8', 'USER'),
('user9', 'password9', 'USER'),
('user10', 'password10', 'USER');

INSERT INTO app.users (login, password, role)
VALUES
('admin', 'admin', 'ADMIN');

INSERT app.users (login, password, role)
VALUES
    ('admin', 'admin', 'ADMIN');

UPDATE app.users
SET role =  SUBSTRING(role FROM 6);

UPDATE app.users
SET role = 'ROLE_' || role;