CREATE TABLE task_categories (
                              id serial PRIMARY KEY,
                              task_id int not null REFERENCES tasks(id),
                              category_id int not null REFERENCES category(id)
);