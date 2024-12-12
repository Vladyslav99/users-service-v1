CREATE TABLE IF NOT EXISTS user_table
(
    ldap_login VARCHAR(50) PRIMARY KEY,
    name       VARCHAR(100),
    surname    VARCHAR(100)
);

INSERT INTO user_table (ldap_login, name, surname)
VALUES ('jdoe-mysql', 'John', 'Doe'),
       ('asmith-mysql', 'Alice', 'Smith'),
       ('bwayne-mysql', 'Bruce', 'Wayne'),
       ('ckent-mysql', 'Clark', 'Kent'),
       ('dprince-mysql', 'Diana', 'Prince'),
       ('dark-mysql', 'Diana', 'Dark'),
       ('hebert-mysql', 'Diana', 'Hebert')
