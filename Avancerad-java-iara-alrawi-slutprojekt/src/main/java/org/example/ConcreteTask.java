package org.example;

public class ConcreteTask extends AbstractTask implements Task {
    public ConcreteTask(int id, String title, String description, boolean updated) {
        super(id, title, description, updated);
    }

    @Override
    public Integer getTaskId() {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isUpdated() {
        return false;
    }
}