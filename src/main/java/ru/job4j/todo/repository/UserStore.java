package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {

    private final SessionFactory sf;

    public Optional<User> add(User u) {
        Session session = sf.openSession();
        Optional user = Optional.empty();
        try {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
            user = Optional.of(u);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        Optional user = Optional.empty();
        try {
            session.beginTransaction();
            user = session.createQuery(
                            "from User WHERE id = :fId")
                    .setParameter("fId", id).uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        Session session = sf.openSession();
        Optional user = Optional.empty();
        try {
            session.beginTransaction();
            user = session.createQuery(
                            "from User WHERE email = :fEmail AND password = :fPassword")
                    .setParameter("fEmail", email)
                    .setParameter("fPassword", password).uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }
}
