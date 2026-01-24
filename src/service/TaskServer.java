package service;

import domain.Task;
import domain.UserSession;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskServer {
    private ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
    private AtomicInteger taskIdGenerator = new AtomicInteger(1);

    public Task createTask(String title, String username) {
        int nextId = taskIdGenerator.getAndIncrement();
        Task task = new Task(nextId, title, username);
        tasks.put(nextId, task);
        return task;
    }


    public Collection<Task> getVisibleTasksFor(String username) {
        return tasks.values()
                .stream()
                .filter(task ->
                        task.getCreatedBy().equals(username) ||
                                (task.getAssignedTo() != null &&
                                        task.getAssignedTo().equals(username))
                )
                .toList();
    }


    public Collection<Task> getAllTasks() {
        return tasks.values();
    }
}
