CREATE TABLE category (
                            id SERIAL PRIMARY KEY,
                            name varchar UNIQUE NOT NULL
);

INSERT INTO category (name) VALUES ('home');
INSERT INTO category (name) VALUES ('job');
INSERT INTO category (name) VALUES ('rest');
INSERT INTO category (name) VALUES ('pet');
