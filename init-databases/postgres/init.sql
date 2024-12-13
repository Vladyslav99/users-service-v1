CREATE TABLE IF NOT EXISTS users
(
    user_id    SERIAL PRIMARY KEY,
    login      VARCHAR(50) NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100)
);

INSERT INTO users (login, first_name, last_name)
VALUES ('jdoe-postgres', 'John', 'Doe'),
       ('andre-postgres', 'John', 'Doe'),
       ('asmith-postgres', 'Alice', 'Smith'),
       ('bwayne-postgres', 'Bruce', 'Wayne'),
       ('ckent-postgres', 'Clark', 'Kent'),
       ('dprince-postgres', 'Diana', 'Prince');
