CREATE TABLE IF NOT EXISTS user_role (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50)
);

INSERT INTO user_role (id, name) VALUES (1, 'employee');
INSERT INTO user_role (id, name) VALUES (2, 'manager');
INSERT INTO user_role (id, name) VALUES (3, 'customer');

CREATE TABLE IF NOT EXISTS user (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
role_id BIGINT,
FOREIGN KEY (role_id) REFERENCES user_role(id)
);

INSERT INTO user (id, name, role_id) VALUES (1, 'user.emp', 1);
INSERT INTO user (id, name, role_id) VALUES (2, 'user.manager', 2);
INSERT INTO user (id, name, role_id) VALUES (3, 'user.customer', 3);


CREATE TABLE IF NOT EXISTS car (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
number VARCHAR(10),
country VARCHAR(2),
);

INSERT INTO car (id, number, country) VALUES (1, 'AB1234', 'BY');
INSERT INTO car (id, number, country) VALUES (2, 'ABC987', 'LT')