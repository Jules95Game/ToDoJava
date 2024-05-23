package org.jules.todolistspring.task;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskSeeder implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        if (taskRepository.count() == 0) {
            taskRepository.saveAll(List.of(
                    new Task("Todo item 1"),
                    new Task("Todo item 2"),
                    new Task("Todo item 3"),
                    new Task("Todo item 4")
            ));
        }
    }
}
