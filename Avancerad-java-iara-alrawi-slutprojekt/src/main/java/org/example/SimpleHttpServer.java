package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class SimpleHttpServer {
    private final HttpServer server;
    private final TaskManager taskManager;

    public SimpleHttpServer(TaskManager taskManager) throws IOException {
        this.taskManager = taskManager;
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/tasks", this::handleRequest);
        server.setExecutor(null); // default executor
    }

    public void start() {
        server.start();
    }

    private void handleRequest(HttpExchange exchange) throws IOException {
        String response = "";
        int statusCode = 200;

        String requestMethod = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if (requestMethod.equals("GET")) {
            if (path.equals("/tasks")) {
                response = handleGetTasks();
            }
        } else if (requestMethod.equals("POST")) {
            if (path.equals("/tasks")) {
                response = handlePostRequest(exchange);
            }
        } else if (requestMethod.equals("PUT")) {
            if (path.startsWith("/tasks/")) {
                response = handlePutRequest(exchange);
            }
        } else if (requestMethod.equals("DELETE")) {
            if (path.startsWith("/tasks/")) {
                response = handleDeleteRequest(exchange);
            }
        }

        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private String handleGetTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

    private String handlePostRequest(HttpExchange exchange) throws IOException {
        String[] requestParts = exchange.getRequestBody().toString().split(",");
        String title = requestParts[0];
        String description = requestParts[1];

        Task newTask = new TaskImpl(taskManager.getNextId(), title, description, false);
        taskManager.addTask(newTask);

        return "Task created with ID " + newTask.getId();
    }

    private String handlePutRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        int taskId = Integer.parseInt(pathParts[2]);

        String[] requestParts = exchange.getRequestBody().toString().split(",");
        String title = requestParts[0];
        String description = requestParts[1];

        Task updatedTask = new TaskImpl(taskId, title, description, true);
        taskManager.updateTask(taskId, updatedTask);

        return "Task with ID " + taskId + " has been updated.";
    }

    private String handleDeleteRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        int taskId = Integer.parseInt(pathParts[2]);

        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.deleteTask(task);
            return "Task with ID " + taskId + " has been deleted.";
        } else {
            return "Task not found.";
        }
    }

    public void startServer() {
    }
}