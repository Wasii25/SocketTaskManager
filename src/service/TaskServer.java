package service;

import domain.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskServer {
    private ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
    private AtomicInteger taskIdGenerator = new AtomicInteger(1);

    public Task createTask(String title) {
        int nextId = taskIdGenerator.getAndIncrement();
        Task task = new Task(nextId, title);
        tasks.put(nextId, task);
        return task;
    }

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }
}
