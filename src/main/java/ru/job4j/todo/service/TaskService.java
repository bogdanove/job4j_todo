package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;

import java.util.List;

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

    public Task findById(int id) {
        return store.findById(id);
    }

    public void delete(int id) {
        store.delete(id);
    }

    public void replace(Task task) {
        store.replace(task);
    }

    public void done(int id) {
        store.done(id);
    }
}
