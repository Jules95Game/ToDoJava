package org.jules.todolistspring.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
