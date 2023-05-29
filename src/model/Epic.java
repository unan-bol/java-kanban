package model;

import java.util.ArrayList;

public class Epic extends Task{
    ArrayList<Integer> subtaskId = new ArrayList<>();

    public Epic(String name, String details, int id, String status) {
        super(name, details, id, status);
    }

    public ArrayList<Integer> getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(ArrayList<Integer> subtaskId) {
        this.subtaskId = subtaskId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name = '" + getName() + '\'' +
                ", details = '" + getDetails() + '\'' +
        ", id = '" + getId() + '\'' +
        ", status = '" + getStatus() +
                "}";
    }
}
