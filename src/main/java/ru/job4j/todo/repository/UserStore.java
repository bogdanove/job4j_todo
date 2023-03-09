package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {

    private final CrudRepository repository;

    public Optional<User> add(User u) {
        Optional<User> user = Optional.empty();
        try {
            repository.run(session -> session.persist(u));
            user = Optional.of(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Optional<User> findById(int id) {
        return repository.optional("from User WHERE id = :fId",
                User.class, Map.of("fId", id));
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return repository.optional("from User WHERE email = :fEmail AND password = :fPassword",
                User.class, Map.of("fEmail", email, "fPassword", password));
    }
}
