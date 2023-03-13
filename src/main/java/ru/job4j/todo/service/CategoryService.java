package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryStore;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryStore store;

    public List<Category> getAllCategory() {
        return store.findAll();
    }

    public List<Category> getAllByIds(List<Integer> ids) {
        return store.findByIds(ids);
    }
}
