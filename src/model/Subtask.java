package model;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String details, int id, Status status, Type type, int epicId) {
        super(name, details, id, status, type);
        this.epicId = epicId;
    }


    public int getEpicId() {

        return epicId;
    }

    public void setEpicId(int epicId) {

        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name = " + getName() + '\'' +
                ", details = " + getDetails() + '\'' +
                ", id = " + getId() + '\'' +
                ", status = " + getStatus() +
                "}";
    }
    @Override
    public String taskToString(){
        return getName() + "," + getDetails() + "," + getId() + "," + getStatus() + "," + getType() + "," + getEpicId();
    }
}
