package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiTaskManager implements TaskManager {

    private static final String BASE_URL = "http://localhost:8000/tasks";

    @Override
    public List<Task> getAllTasks() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return parseTasks(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public int getNextId() {
        return 0;
    }

    @Override
    public Task getTaskById(int taskId) {
        return null;
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void addTask(Task task) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(taskToJson(task).getBytes());
            os.flush();
            os.close();

            connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(int id, Task updatedTask) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(taskToJson(updatedTask).getBytes());
            os.flush();
            os.close();

            connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllTasks() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Task> parseTasks(String json) {
        return new ArrayList<>();
    }

    private String taskToJson(Task task) {
        return "{}";
    }
}