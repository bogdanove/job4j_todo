package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserStore;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStore store;

    public Optional<User> add(User u) {
        return store.add(u);
    }

    public Optional<User> findById(int id) {
        return store.findById(id);
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return store.findUserByEmailAndPassword(email, password);
    }

}
