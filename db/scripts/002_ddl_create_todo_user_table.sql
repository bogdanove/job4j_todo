create table if not exists todo_user
(
    id        serial primary key,
    name      varchar        not null,
    email     varchar unique not null,
    password  varchar        not null
);

ALTER TABLE tasks ADD COLUMN user_id int not null references todo_user(id);