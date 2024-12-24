package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private TaskManager taskManager;
    private JTextArea taskArea;
    private JButton addButton;
    private JButton showAllButton;
    private JButton deleteAllButton;
    private JButton updateButton;
    private JLabel statusLabel;

    public MainFrame() {
        taskManager = new InMemoryTaskManager();
        taskArea = new JTextArea(20, 50);
        addButton = new JButton("Add Task");
        showAllButton = new JButton("Show All Tasks");
        deleteAllButton = new JButton("Delete All Tasks");
        updateButton = new JButton("Update Task");
        statusLabel = new JLabel("");


        setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(deleteAllButton);
        buttonPanel.add(updateButton);


        JPanel statusPanel = new JPanel();
        statusLabel.setForeground(Color.GREEN);
        statusPanel.add(statusLabel);


        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskArea), BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter task title:");
                String description = JOptionPane.showInputDialog("Enter task description:");
                Task task = new ConcreteTask(taskManager.getNextId(), title, description, false);
                taskManager.addTask(task);
                updateTaskList();
            }
        });


        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTaskList();
            }
        });

        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManager.deleteAllTasks();
                updateTaskList();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskIdStr = JOptionPane.showInputDialog("Enter task ID to update:");
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    String newTitle = JOptionPane.showInputDialog("Enter new task title:");
                    String newDescription = JOptionPane.showInputDialog("Enter new task description:");
                    Task updatedTask = new ConcreteTask(taskId, newTitle, newDescription, true);
                    taskManager.updateTask(taskId, updatedTask);
                    updateTaskList();
                    showStatusMessage("Task Updated!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid task ID");
                }
            }
        });

        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    public void updateTaskList() {
        taskArea.setText("");
        List<Task> tasks = taskManager.getAllTasks();
        for (Task task : tasks) {
            taskArea.append(task.getId() + ": " + task.getTitle() + " - " + task.getDescription() + "\n");
        }
    }

    public void showStatusMessage(String message) {
        statusLabel.setText(message);
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}