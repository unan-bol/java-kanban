package model;

public class SimpleTask extends Task{
    public SimpleTask(String name, String details, int id, Status status, Type type) {

        super(name, details, id, status, type);
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
