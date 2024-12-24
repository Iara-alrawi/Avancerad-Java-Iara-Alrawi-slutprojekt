package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TaskManager taskManager = new InMemoryTaskManager();
                SimpleHttpServer server = new SimpleHttpServer(taskManager);
                server.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}