package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleHttpServer {
    private TaskManager taskManager;

    public SimpleHttpServer(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/tasks", this::handleRequests);
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8000");
    }

    private void handleRequests(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        int statusCode = 200;

        switch (method) {
            case "GET":
                response = handleGetRequest();
                break;
            case "POST":
                response = handlePostRequest(exchange);
                break;
            case "PUT":
                response = handlePutRequest(exchange);
                break;
            case "DELETE":
                response = handleDeleteRequest(exchange);
                break;
            default:
                statusCode = 405;
                response = "Invalid Request Method";
        }


        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String handleGetRequest() {
        List<Task> tasks = taskManager.getAllTasks();
        return tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }


    private String handlePostRequest(HttpExchange exchange) throws IOException {
        InputStreamReader reader = new InputStreamReader(exchange.getRequestBody());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String body = bufferedReader.lines().collect(Collectors.joining("\n"));

        String[] parts = body.split("\n");
        String title = parts[0];
        String description = parts[1];

        Task task = new ConcreteTask(taskManager.getNextId(), title, description, false);
        taskManager.addTask(task);
        return "Task added: " + task.toString();
    }


    private String handlePutRequest(HttpExchange exchange) throws IOException {
        InputStreamReader reader = new InputStreamReader(exchange.getRequestBody());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String body = bufferedReader.lines().collect(Collectors.joining("\n"));

        String[] parts = body.split("\n");
        int taskId = Integer.parseInt(parts[0]);
        String newTitle = parts[1];
        String newDescription = parts[2];

        Task updatedTask = new ConcreteTask(taskId, newTitle, newDescription, true);
        taskManager.updateTask(taskId, updatedTask);
        return "Task updated: " + updatedTask.toString();
    }


    private String handleDeleteRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        int taskId = Integer.parseInt(path.split("/")[2]);


        return path;
    }

    }
