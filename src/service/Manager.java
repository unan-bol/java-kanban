package service;

import model.Epic;
import model.SimpleTask;
import model.Subtask;
import java.util.ArrayList;
import java.util.HashMap;

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

    public int saveSubtask(Subtask subtask, int epicId){
        subtask.setId(nextId);
        nextId++;
        subtasks.put(subtask.getId(),  subtask);
        subtask.setEpicId(epicId);

        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtaskId = new ArrayList<>();
        subtaskId = epic.getSubtaskId();
        subtaskId.add(subtask.getId());
        epic.setSubtaskId(subtaskId);
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
            epics.get(id).getSubtaskId().clear();
        }
    }

    public void deleteById(int id){
        if (simpleTasks.containsKey(id)){
            simpleTasks.remove(id);
        } else if (epics.containsKey(id)){
            epics.remove(id);
            for(Integer i: subtasks.keySet()){
                Subtask subtask = subtasks.get(i);
                if (subtask.getEpicId() == id){
                    subtasks.remove(id);
                }
            }
        } else if (subtasks.containsKey(id)){
            subtasks.remove(id);
            epics.get(id).getSubtaskId().remove(id);
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

        String status = "NEW";
        boolean isDone = true;
        boolean isProgress  = false;

        for (int i = 0; i < epic.getSubtaskId().size(); i++) {
            Subtask subtask = subtasks.get(epic.getSubtaskId().get(i));
            if (subtask.getStatus().equals("IN_PROGRESS")) {
                epic.setStatus("IN_PROGRESS");
                isProgress = true;
                break;
            } else if (subtask.getStatus().equals("NEW")) {
                isDone = false;
            } else if ((subtask.getStatus().equals("DONE")) && (isDone)) {
                epic.setStatus("IN_PROGRESS");
                isProgress = true;
                break;
            }
        }
        if ((isDone) && (!isProgress)&&(!epic.getSubtaskId().isEmpty())){
            epic.setStatus("DONE");
        } else if ((!isDone) &&  (!isProgress)){
            epic.setStatus("NEW");
        }
    }

    public ArrayList<SimpleTask> getAllSimpleTasks(){
        ArrayList<SimpleTask> allSimpleTask= new ArrayList<>();
        for(Integer id: simpleTasks.keySet()){
            allSimpleTask.add(simpleTasks.get(id));
        }
        return allSimpleTask;
    }

    public ArrayList<Epic> getAllEpics(){
        ArrayList<Epic> allEpic= new ArrayList<>();
        for(Integer id: epics.keySet()){
            allEpic.add(epics.get(id));
        }
        return allEpic;
    }

    public ArrayList<Subtask> getAllSubtasks(){
        ArrayList<Subtask> allSubtask= new ArrayList<>();
        for(Integer id: subtasks.keySet()){
            allSubtask.add(subtasks.get(id));
        }
        return allSubtask;
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
        for  (Subtask task: subtasks.values()){
            if (task.getEpicId() == epic.getId()){
                subtask.add(task);
            }
        }
    return subtask;
    }

}
