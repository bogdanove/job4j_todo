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
public class TasksController {

    private TaskService taskService;

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/addTask")
    public String formAddTask() {
        return "addTask";
    }

    @PostMapping("/createTask")
    public String createPost(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/allNew")
    public String getNewTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllNew());
        return "tasks";
    }

    @GetMapping("/allDone")
    public String getDoneTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllDone());
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String getOneTasks(Model model, @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id));
        return "task";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/formUpdateTask/{id}")
    public String formUpdateTask(Model model, @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id));
        return "formUpdateTask";
    }

    @PostMapping("/update")
    public String doneTask(@ModelAttribute Task task) {
        taskService.replace(task);
        return "redirect:/tasks";
    }

    @GetMapping("/done/{id}")
    public String doneTask(@PathVariable int id) {
        taskService.done(id);
        return "redirect:/tasks";
    }
}
