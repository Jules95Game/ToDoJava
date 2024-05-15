package org.jules.todolistspring.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask, UriComponentsBuilder ucb) {
        Task savedTask = taskRepository.save(newTask);
        URI location = ucb.path("task/{id}").buildAndExpand(savedTask.getId()).toUri();
        return ResponseEntity.created(location).body(savedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable UUID id) {
        Optional<Task> oldTask = taskRepository.findById(id);
        if (oldTask.isEmpty()) return notFound();
        taskRepository.delete(oldTask.get());
        return ResponseEntity.noContent().build();
    }
}
