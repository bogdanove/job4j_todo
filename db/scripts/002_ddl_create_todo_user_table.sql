create table if not exists todo_user
(
    id        serial primary key,
    name      varchar        not null,
    email     varchar unique not null,
    password  varchar        not null
);

insert into todo_user (name, email, password) values ('Name', 'mail@mail.ru', 'qwerty');