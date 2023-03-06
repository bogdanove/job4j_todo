package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

    private TaskService taskService;

    @GetMapping()
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/all";
    }

    @GetMapping("/add")
    public String formAddTask() {
        return "tasks/add";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/allNew")
    public String getNewTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllNew());
        return "tasks/all";
    }

    @GetMapping("/allDone")
    public String getDoneTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllDone());
        return "tasks/all";
    }

    @GetMapping("/task/{id}")
    public String getOneTasks(Model model, @PathVariable int id) {
        var task = taskService.findById(id);
        if (task.isEmpty()) {
            model.addAttribute("message", "Произошла ошибка, задача не найдена");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
        return "tasks/current";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(Model model, @PathVariable int id) {
        var result = taskService.delete(id);
        if (!result) {
            model.addAttribute("message", "Произошла ошибка, задача не удалена");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdateTask(Model model, @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id));
        return "tasks/formUpdate";
    }

    @PostMapping("/update")
    public String doneTask(Model model, @ModelAttribute Task task) {
        var result = taskService.replace(task);
        if (!result) {
            model.addAttribute("message", "Произошла ошибка, обновление не выполнено!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/done/{id}")
    public String doneTask(Model model, @PathVariable int id) {
        var result = taskService.done(id);
        if (!result) {
            model.addAttribute("message", "Произошла ошибка, завершение задачи не выполнено!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }
}
