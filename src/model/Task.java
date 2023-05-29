package model;

public class Task {
    private String name;
    private String details;
    private int id;
    private String status;

    public Task(String name, String details, int id, String status) {
        this.name = name;
        this.details = details;
        this.id = id;
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

