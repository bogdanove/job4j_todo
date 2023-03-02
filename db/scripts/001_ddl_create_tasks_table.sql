CREATE TABLE if not exists tasks (
                       id SERIAL PRIMARY KEY,
                       name varchar not null,
                       description TEXT,
                       created TIMESTAMP,
                       done BOOLEAN
);

insert into tasks (name, description, created, done)
 values ('Вынести мусор', 'Что бы на кухне не воняло, необходимо в кратчайшие сроки выкинуть мусор!', now(), false);
insert into tasks (name, description, created, done)
 values ('Купить продукты', 'Что бы не умереть с голоду, необходимо закупиться продуктами!', now(), false);
insert into tasks (name, description, created, done)
 values ('Вечерняя прогулка', 'Что бы лучше себя чувствовать, необходимо выполнять ежедневные прогулся!', now(), false);