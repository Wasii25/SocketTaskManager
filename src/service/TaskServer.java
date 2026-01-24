package service;

import domain.Task;
import domain.UserSession;
import repository.TaskRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskServer {
    private final TaskRepository repository;
    private AtomicInteger taskIdGenerator = new AtomicInteger(1);
    public TaskServer(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String title, String username) {
        int id = taskIdGenerator.getAndIncrement();
        Task task = new Task(id, title, username);
        return repository.save(task);
    }

    public List<Task> getVisibleTasksFor(String username) {
        return repository.findVisibleTasksFor(username);
    }

}
