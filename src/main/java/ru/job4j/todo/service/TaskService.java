package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskStore store;

    public List<Task> findAll() {
        return store.findAll();
    }

    public Task add(Task task) {
        return store.add(task);
    }

    public List<Task> findAllNew() {
        return store.findAllNew();
    }

    public List<Task> findAllDone() {
        return store.findAllDone();
    }

    public Optional<Task> findById(int id) {
        return store.findById(id);
    }

    public boolean delete(int id) {
       return store.delete(id);
    }

    public boolean replace(Task task) {
        return store.replace(task);
    }

    public boolean done(int id) {
        return store.done(id);
    }
}
