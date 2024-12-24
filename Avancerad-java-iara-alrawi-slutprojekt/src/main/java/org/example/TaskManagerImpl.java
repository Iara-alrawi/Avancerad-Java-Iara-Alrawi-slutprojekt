package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerImpl implements TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManagerImpl() {
        tasks = new ArrayList<>();
        nextId = 1; // Börja med ID 1
    }

    @Override
    public int getNextId() {
        return nextId++;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void updateTask(int id, Task updatedTask) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            tasks.add(updatedTask);
        }
    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}