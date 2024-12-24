package org.example;

public class ConcreteTask implements Task {
    private int id;
    private String title;
    private String description;
    private boolean updated;

    public ConcreteTask(int id, String title, String description, boolean updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.updated = updated;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(boolean updated) {
        
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Description: " + description;
    }
}