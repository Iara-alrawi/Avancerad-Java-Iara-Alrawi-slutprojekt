package org.example;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int currentId = 1;

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void updateTask(int id, Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.set(i, task);
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
        return currentId++;
    }
}