package org.example;

import java.util.List;

public interface TaskManager {
    void addTask(Task task);
    void updateTask(int id, Task task);
    void deleteAllTasks();
    List<Task> getAllTasks();
    int getNextId();
}