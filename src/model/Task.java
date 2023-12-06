package model;

public class Task {
    private String name;
    private String details;
    private int id;
    private Status status;

    private Type type;

    public Task(String name, String details, int id, Status status, Type type) {
        this.name = name;
        this.details = details;
        this.id = id;
        this.status = status;
        this.type = type;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String taskToString(){
        return getName() + "," + getDetails() + "," + getId() + "," + getStatus() + "," + getType();
    }

}

