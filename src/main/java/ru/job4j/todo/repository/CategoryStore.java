package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CategoryStore {

    private final CrudRepository repository;

    public List<Category> findAll() {
        return repository.query("from Category", Category.class);
    }

    public List<Category> findByIds(List<Integer> ids) {
        return repository.query("from Category where id in (:ids)", Category.class, Map.of("ids", ids));
    }
}
