package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                            "from Task where created > :fMinusHour")
                    .setParameter("fMinusHour", LocalDateTime.now().minusHours(1)).list();
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

    public Task findById(int id) {
        Session session = sf.openSession();
        Task item = new Task();
        try {
            session.beginTransaction();
            item = (Task) session.createQuery(
                            "from Task WHERE id = :fId")
                    .setParameter("fId", id).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return item;
    }

    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void replace(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET name = :fName, description = :fDescription, done = :fDone WHERE id = :fId")
                    .setParameter("fName", task.getName())
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void done(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET done = true WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}

