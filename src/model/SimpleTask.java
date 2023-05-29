package model;

public class SimpleTask extends Task{
    public SimpleTask(String name, String details, int id, String status) {
        super(name, details, id, status);
    }
    @Override
    public String toString() {
        return "SimpleTask{" +
                "name = " + getName() + '\'' +
                ", details = " + getDetails() + '\'' +
                ", id = " + getId() + '\'' +
                ", status = " + getStatus() +
                "}";
    }
}
