CREATE TABLE IF NOT EXISTS  USER_ROLE (
ID BIGINT auto_increment,
NAME VARCHAR(50),
PRIMARY KEY (ID)
);

INSERT INTO USER_ROLE (ID, NAME) VALUES (1, 'user');
INSERT INTO USER_ROLE (ID, NAME) VALUES (2, 'admin');

CREATE TABLE IF NOT EXISTS  "USER" (
ID BIGINT auto_increment,
NAME VARCHAR(50),
ROLE_ID BIGINT,
PRIMARY KEY (ID),
FOREIGN KEY (ROLE_ID) REFERENCES USER_ROLE(ID)
);

INSERT INTO "USER" (ID, NAME, ROLE_ID) VALUES (1, 'user.simple', 1);
INSERT INTO "USER" (ID, NAME, ROLE_ID) VALUES (2, 'user.admin', 2);

CREATE TABLE IF NOT EXISTS "CAR" (
ID BIGINT auto_increment,
NUMBER VARCHAR(10),
COUNTRY VARCHAR(2),
PRIMARY KEY (ID)
);

INSERT INTO "CAR" (ID, NUMBER, COUNTRY) VALUES (1, 'AB1234', 'BY');
INSERT INTO "CAR" (ID, NUMBER, COUNTRY) VALUES (2, 'ABC987', 'LT')