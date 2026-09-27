package com.example.firstapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {

        model.addAttribute("tasks", taskRepository.findAll());

        return "index";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String title) {

        Task task = new Task(title);

        taskRepository.save(task);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {

        Task task = taskRepository.findById(id).orElseThrow();

        task.setCompleted(true);

        taskRepository.save(task);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {

    taskRepository.deleteById(id);

    return "redirect:/tasks";
}

@PostMapping("/tasks/{id}/edit")
public String editTask(@PathVariable Long id,
                       @RequestParam String title) {

    Task task = taskRepository.findById(id).orElseThrow();

    task.setTitle(title);

    taskRepository.save(task);

    return "redirect:/tasks";
}
}