package org.jules.todolistspring.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    private ResponseEntity<Task> notFound() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @PutMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask, UriComponentsBuilder ucb) {
        Task savedTask = taskRepository.save(newTask);
        URI location = ucb.path("task/{id}").buildAndExpand(savedTask.getId()).toUri();
        return ResponseEntity.created(location).body(savedTask);
    }
}