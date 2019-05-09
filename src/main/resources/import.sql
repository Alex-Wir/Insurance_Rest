CREATE TABLE IF NOT EXISTS role (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS user (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS car (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 number VARCHAR(10),
 country VARCHAR(2),
);

CREATE TABLE IF NOT EXISTS pos (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20),
  point_id BIGINT,
  FOREIGN KEY (point_id) REFERENCES point(id)
);

CREATE TABLE IF NOT EXISTS shift (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   openingTime DATETIME,
   closingTime DATETIME,
   user_id BIGINT,
   pos_id BIGINT,
   FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS insurance (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  payment FLOAT,
  amount FLOAT,
  car_id BIGINT,
  shift_id BIGINT,
  user_id BIGINT,
  FOREIGN KEY (car_id) REFERENCES car(id),
  FOREIGN KEY (shift_id) REFERENCES shift(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (3, 'ROLE_CUSTOMER');

INSERT INTO user (id, name) VALUES (1, 'grodno.emp1');
INSERT INTO user (id, name) VALUES (2, 'grodno.emp2');
INSERT INTO user (id, name) VALUES (3, 'grodno.manager');
INSERT INTO user (id, name) VALUES (4, 'lida.emp1');
INSERT INTO user (id, name) VALUES (5, 'lida.emp2');
INSERT INTO user (id, name) VALUES (6, 'lida.manager');
INSERT INTO user (id, name) VALUES (7, 'top.manager');
INSERT INTO user (id, name) VALUES (8, 'customer1');
INSERT INTO user (id, name) VALUES (9, 'customer2');
INSERT INTO user (id, name) VALUES (10, 'new.user1');
INSERT INTO user (id, name) VALUES (11, 'new.user2');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO user_role (user_id, role_id) VALUES (5, 1);
INSERT INTO user_role (user_id, role_id) VALUES (6, 1);
INSERT INTO user_role (user_id, role_id) VALUES (6, 2);
INSERT INTO user_role (user_id, role_id) VALUES (7, 2);
INSERT INTO user_role (user_id, role_id) VALUES (8, 3);
INSERT INTO user_role (user_id, role_id) VALUES (9, 1);
INSERT INTO user_role (user_id, role_id) VALUES (10, 1);

INSERT INTO car (id, number, country) VALUES (1, 'AB1234', 'BY');
INSERT INTO car (id, number, country) VALUES (2, 'ABC987', 'LT');
INSERT INTO car (id, number, country) VALUES (3, '1234KW', 'PL');

INSERT INTO address (id, city, zipcode) VALUES (1, 'Grodno', 230000);
INSERT INTO address (id, city, zipcode) VALUES (2, 'Lida', 231000);

INSERT INTO point (id, name, address_id) VALUES (1, 'Grodno office', 1);
INSERT INTO point (id, name, address_id) VALUES (2, 'Lida office', 2);

INSERT INTO pos (id, name, point_id) VALUES (1, 'Grodno main pos', 1);
INSERT INTO pos (id, name, point_id) VALUES (2, 'Grodno reserve pos', 1);
INSERT INTO pos (id, name, point_id) VALUES (3, 'Lida main pos', 2);
INSERT INTO pos (id, name, point_id) VALUES (4, 'Lida reserve pos', 2);

INSERT INTO point_user (point_id, user_id) VALUES (1, 1);
INSERT INTO point_user (point_id, user_id) VALUES (1, 2);
INSERT INTO point_user (point_id, user_id) VALUES (1, 3);
INSERT INTO point_user (point_id, user_id) VALUES (2, 4);
INSERT INTO point_user (point_id, user_id) VALUES (2, 5);
INSERT INTO point_user (point_id, user_id) VALUES (2, 6);
INSERT INTO point_user (point_id, user_id) VALUES (1, 7);
INSERT INTO point_user (point_id, user_id) VALUES (2, 7);

INSERT INTO shift (id, openingTime, closingTime, user_id, pos_id) VALUES (1, '2019-05-05 09:00:00', '2019-05-05 20:59:59', 1, 1);
INSERT INTO shift (id, openingTime, closingTime, user_id, pos_id) VALUES (2, '2019-05-05 21:00:00', '2019-05-06 08:30:00', 2, 1);
INSERT INTO shift (id, openingTime, closingTime, user_id, pos_id) VALUES (3, '2019-05-05 08:00:00', '2019-05-05 20:00:00', 4, 3);
INSERT INTO shift (id, openingTime, closingTime, user_id, pos_id) VALUES (4, '2019-05-05 21:00:00', '2019-05-06 08:30:00', 5, 3);

INSERT INTO insurance (id, payment, amount, car_id, shift_id, user_id) VALUES (1, 12, 600, 1, 1, 8);
INSERT INTO insurance (id, payment, amount, car_id, shift_id, user_id) VALUES (2, 15, 800, 2, 2, 10);
INSERT INTO insurance (id, payment, amount, car_id, shift_id, user_id) VALUES (3, 20, 900, 3, 3, 8);
INSERT INTO insurance (id, payment, amount, car_id, shift_id, user_id) VALUES (4, 10, 700, 1, 4, 10)





