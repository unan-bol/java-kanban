package service;

import model.Epic;
import model.SimpleTask;
import model.Subtask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

public class Manager {
    private int nextId = 1;
    public HashMap<Integer, SimpleTask> simpleTasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public int saveSimpleTask(SimpleTask task){
        task.setId(nextId);
        nextId++;
        simpleTasks.put(task.getId(), task);
        return task.getId();
    }

    public int saveEpic(Epic epic){
        epic.setId(nextId);
        nextId++;
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    public int saveSubtask(Subtask subtask){
        subtask.setId(nextId);
        nextId++;
        subtasks.put(subtask.getId(),  subtask);

        Epic epic = epics.get(subtask.getEpicId());
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        subtaskIds.add(subtask.getId());
        epic.setSubtaskIds(subtaskIds);
        updateEpicStatus(epic);
        return subtask.getId();
    }

    public void deleteSimpleTasks(){
        simpleTasks.clear();
    }
    public void deleteEpic(){
        epics.clear();
        subtasks.clear();
    }
    public void deleteSubTask(){
        subtasks.clear();
        for(Integer id: epics.keySet()){
            epics.get(id).getSubtaskIds().clear();
            updateEpicStatus(epics.get(id));
        }
    }

    public void deleteById(int id){
        if (simpleTasks.containsKey(id)){
            simpleTasks.remove(id);
        } else if (epics.containsKey(id)){
            Subtask subtask = subtasks.get(epics.remove(id).getId());
            if (subtask.getEpicId() == id){
                subtasks.remove(id);
            }
        } else if (subtasks.containsKey(id)){
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpicId());
            for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
                if (epic.getSubtaskIds().get(i) == subtask.getId()){
                    epic.getSubtaskIds().remove(i);
                }
            }
            subtasks.remove(id);
            updateEpicStatus(epic);
        }
    }

    public void updateSimpleTask(SimpleTask simpleTask, int id){
        simpleTasks.put(id, simpleTask);
        simpleTask.setId(id);
    }

    public void updateSubtask(Subtask subtask, int id){
        subtasks.put(id, subtask);
        subtask.setId(id);
    }

    public void updateEpic(Epic epic, int id) {
        epics.put(id, epic);
        epic.setId(id);
        updateEpicStatus(epic);
    }

    public ArrayList<SimpleTask> getAllSimpleTasks(){
        return new ArrayList<>(simpleTasks.values());
    }

    public ArrayList<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    public SimpleTask getSimpleTaskById(int id){
        return simpleTasks.get(id);
    }

    public Subtask getSubtaskById(int id){
        return subtasks.get(id);
    }

    public Epic getEpicById(int id){
        return epics.get(id);
    }

    public ArrayList<Subtask> getSubtaskByEpic(Epic epic){
        ArrayList<Subtask> subtask= new ArrayList<>();
        for  (Integer id: epic.getSubtaskIds()){
            subtask.add(subtasks.get(id));
        }
    return subtask;
    }

    private void updateEpicStatus(Epic epic){
        String status = "NEW";
        boolean isDone = true;
        boolean isProgress  = false;

        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            Subtask subtask = subtasks.get(epic.getSubtaskIds().get(i));
            if (subtask.getStatus().equals("IN_PROGRESS")) {
                status  = "IN_PROGRESS";
                isProgress = true;
                break;
            } else if (subtask.getStatus().equals("NEW")) {
                isDone = false;
            } else if ((subtask.getStatus().equals("DONE")) && (!isDone)) {
                status = "IN_PROGRESS";
                isProgress = true;
                break;
            }
        }
        if ((isDone) && (!isProgress)&&(!epic.getSubtaskIds().isEmpty())){
            epic.setStatus("DONE");
        } else if ((!isDone) &&  (!isProgress)){
            epic.setStatus("NEW");
        } else{
            epic.setStatus("IS_PROGRESS");
        }
    }

}
