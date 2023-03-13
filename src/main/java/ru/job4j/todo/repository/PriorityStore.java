package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriorityStore {

    private final CrudRepository repository;

    public List<Priority> findAll() {
        return repository.query("from Priority", Priority.class);
    }

    public Optional<Priority> findPriorityById(int id) {
        return repository.optional("from Priority where id = :id", Priority.class, Map.of("id", id));
    }
}
