package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {

    private final CrudRepository repository;

    public List<Task> findAll() {
        return repository.query("from Task t join fetch t.priority", Task.class);
    }

    public List<Task> findAllDone() {
        return repository.query("from Task t join fetch t.priority where done = true", Task.class);
    }

    public List<Task> findAllNew() {
        return repository.query("from Task t join fetch t.priority where done = false", Task.class);
    }

    public Task add(Task task) {
        repository.run(session -> session.persist(task));
        return task;
    }

    public Optional<Task> findById(int id) {
        return repository.optional("from Task WHERE id = :fId", Task.class, Map.of("fId", id));
    }

    public boolean delete(int id) {
        return repository.run("DELETE Task WHERE id = :fId", Map.of("fId", id)) > 0;
    }

    public boolean replace(Task task) {
        return repository.run(
                "UPDATE Task SET name = :fName, description = :fDescription, done = :fDone WHERE id = :fId",
                Map.of("fName", task.getName(), "fDescription", task.getDescription(), "fDone",
                        task.isDone(), "fId", task.getId())) > 0;
    }

    public boolean done(int id) {
        return repository.run("UPDATE Task SET done = true WHERE id = :fId",
                Map.of("fId", id)) > 0;
    }
}

