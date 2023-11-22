CREATE SCHEMA IF NOT EXISTS users_schema;

CREATE TABLE IF NOT EXISTS users_schema.users(
    login       VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(40),
    role        VARCHAR(20),
    farmer_id   INT
);

INSERT INTO users_schema.users (login, password, role, farmer_id)
    VALUES
('user1', 'password1', 'USER', 1),
('user2', 'password2', 'USER', 2),
('user3', 'password3', 'USER', 3),
('user4', 'password4', 'USER', 4),
('user5', 'password5', 'USER', 5),
('user6', 'password6', 'USER', 6),
('user7', 'password7', 'USER', 7),
('user8', 'password8', 'USER', 8),
('user9', 'password9', 'USER', 9),
('user10', 'password10', 'USER', 10);