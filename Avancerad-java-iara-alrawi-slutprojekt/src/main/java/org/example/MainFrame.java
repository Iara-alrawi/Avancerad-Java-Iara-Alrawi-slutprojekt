package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private TaskManager taskManager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JButton addButton, updateButton, showAllButton, deleteAllButton;
    private JLabel statusLabel;

    public MainFrame() {

        taskManager = new InMemoryTaskManager();


        setTitle("To-Do List");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());


        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea();
        descriptionArea.setRows(3);
        inputPanel.add(new JScrollPane(descriptionArea));

        addButton = new JButton("Add Task");
        updateButton = new JButton("Update Task");
        showAllButton = new JButton("Show All Tasks");
        deleteAllButton = new JButton("Delete All Tasks");

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(showAllButton);
        inputPanel.add(deleteAllButton);

        add(inputPanel, BorderLayout.SOUTH);
        statusLabel = new JLabel("");
        add(statusLabel, BorderLayout.NORTH);


        addButton.addActionListener(e -> addTask());
        updateButton.addActionListener(e -> updateTask());
        showAllButton.addActionListener(e -> loadTasks());
        deleteAllButton.addActionListener(e -> deleteAllTasks());

        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        taskListModel.clear();


        int taskId = 1;
        for (Task task : tasks) {
            taskListModel.addElement("Nr " + taskId++ + ": " + task.getTitle() + " - " + task.getDescription());
        }
    }

    private void addTask() {
        String title = titleField.getText();
        String description = descriptionArea.getText();

        if (!title.isEmpty() && !description.isEmpty()) {
            Task newTask = new TaskImpl(taskManager.getNextId(), title, description, false);
            taskManager.addTask(newTask);
            loadTasks();
            titleField.setText("");
            descriptionArea.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both title and description");
        }
    }

    private void updateTask() {
        String selectedTask = taskList.getSelectedValue();
        if (selectedTask != null) {

            String taskIdString = selectedTask.split(":")[0].trim().split(" ")[1];
            int taskId = Integer.parseInt(taskIdString);


            String newTitle = titleField.getText();
            String newDescription = descriptionArea.getText();

            if (!newTitle.isEmpty() && !newDescription.isEmpty()) {

                Task updatedTask = new TaskImpl(taskId, newTitle, newDescription, true);
                taskManager.updateTask(taskId, updatedTask);
                loadTasks();
                titleField.setText("");
                descriptionArea.setText("");
                statusLabel.setText("Task " + taskId + " has been updated.");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both title and description for update");
            }
        }
    }

    private void deleteAllTasks() {
        taskManager.deleteAllTasks();
        loadTasks();
        statusLabel.setText("All tasks have been deleted.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}