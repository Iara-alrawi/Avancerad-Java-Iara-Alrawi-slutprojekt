package org.example;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void updateTask(int id, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                tasks.add(updatedTask);
                break;
            }
        }
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public int getNextId() {
        return nextId++;
    }
}