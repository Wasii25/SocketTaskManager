package repository;

import domain.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);

    List<Task> findAll();

    List<Task> findVisibleTasksFor(String username);
}
