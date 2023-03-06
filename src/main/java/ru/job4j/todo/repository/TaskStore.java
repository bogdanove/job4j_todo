package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {

    private final SessionFactory sf;

    public List<Task> findAll() {
        Session session = sf.openSession();
        List tasks = new ArrayList();
        try {
            session.beginTransaction();
            tasks = session.createQuery(
                    "from Task").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return tasks;
    }

    public List<Task> findAllDone() {
        Session session = sf.openSession();
        List tasks = new ArrayList();
        try {
            session.beginTransaction();
            tasks = session.createQuery(
                    "from Task where done = true").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return tasks;
    }

    public List<Task> findAllNew() {
        Session session = sf.openSession();
        List tasks = new ArrayList();
        try {
            session.beginTransaction();
            tasks = session.createQuery(
                    "from Task where done = false").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return tasks;
    }

    public Task add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return task;
    }

    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Optional task = Optional.empty();
        try {
            session.beginTransaction();
            task = session.createQuery(
                            "from Task WHERE id = :fId")
                    .setParameter("fId", id).uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return task;
    }

    public boolean delete(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            result = count > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    public boolean replace(Task task) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "UPDATE Task SET name = :fName, description = :fDescription, done = :fDone WHERE id = :fId")
                    .setParameter("fName", task.getName())
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            result = count > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    public boolean done(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "UPDATE Task SET done = true WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            result = count > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }
}

