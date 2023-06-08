package model;

import java.util.ArrayList;

public class Epic extends Task{
    ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String details, int id, Status status) {

        super(name, details, id, status);
    }

    public ArrayList<Integer> getSubtaskIds() {

        return subtaskIds;
    }

    public void setSubtaskIds(ArrayList<Integer> subtaskId) {

        this.subtaskIds = subtaskId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name = '" + getName() + '\'' +
                ", details = '" + getDetails() + '\'' +
        ", id = '" + getId() + '\'' +
        ", status = " + getStatus() +
                "}";
    }
}
