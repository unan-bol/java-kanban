package service;

import model.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager{
    private int nextId = 1;
    public HashMap<Integer, SimpleTask> simpleTasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public HistoryManager historyManager = new InMemoryHistoryManager();


    @Override
    public int saveSimpleTask(SimpleTask task){
        task.setId(nextId);
        nextId++;
        simpleTasks.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int saveEpic(Epic epic){
        epic.setId(nextId);
        nextId++;
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    @Override
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

    @Override
    public void deleteSimpleTasks(){
        for (SimpleTask simpleTask: simpleTasks.values()){
            historyManager.remove(simpleTask.getId());
        }
        simpleTasks.clear();

    }

    @Override
    public void deleteEpic(){
        for (Epic epic: epics.values()){
            historyManager.remove(epic.getId());
        }
        for (Subtask subtask: subtasks.values()){
            historyManager.remove(subtask.getId());
        }
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteSubTask(){
        for (Subtask subtask: subtasks.values()){
            historyManager.remove(subtask.getId());
        }
        subtasks.clear();
        for(Integer id: epics.keySet()){
            epics.get(id).getSubtaskIds().clear();
            updateEpicStatus(epics.get(id));
        }
    }

    @Override
    public void deleteById(int id){
        if (simpleTasks.containsKey(id)){
            SimpleTask simpleTask = simpleTasks.get(id);
            historyManager.remove(id);
            simpleTasks.remove(id);
        } else if (epics.containsKey(id)){
            Epic epic = epics.get(id);
            //for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            for (int i:epic.getSubtaskIds()) {
                historyManager.remove(i);
                subtasks.remove(i);
            }
            historyManager.remove(id);
            epics.remove(id);
        } else if (subtasks.containsKey(id)){
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpicId());
            for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
                if (epic.getSubtaskIds().get(i) == subtask.getId()){
                    epic.getSubtaskIds().remove(i);
                }
            }
            historyManager.remove(id);
            subtasks.remove(id);
            updateEpicStatus(epic);
        }
    }

    @Override
    public void updateSimpleTask(SimpleTask simpleTask, int id){
        simpleTasks.put(id, simpleTask);
        simpleTask.setId(id);
    }

    @Override
    public void updateSubtask(Subtask subtask, int id){
        subtasks.put(id, subtask);
        subtask.setId(id);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        epics.put(id, epic);
        epic.setId(id);
        updateEpicStatus(epic);
    }

    @Override
    public List<SimpleTask> getAllSimpleTasks(){
        for (Task task: simpleTasks.values()) {
            historyManager.add(task);
        }
        return new ArrayList<>(simpleTasks.values());
    }

    @Override
    public List<Epic> getAllEpics(){
        for (Task task: epics.values()) {
            historyManager.add(task);
        }
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks(){
        for (Task task: subtasks.values()) {
            historyManager.add(task);
        }
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public SimpleTask getSimpleTaskById(int id){
        SimpleTask task = simpleTasks.get(id);
        if (task != null){
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Subtask getSubtaskById(int id){
        Subtask task = subtasks.get(id);
        if (task != null){
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Epic getEpicById(int id){
        Epic task = epics.get(id);
        if (task != null){
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public List<Subtask> getSubtaskByEpic(Epic epic){
        List<Subtask> subtask= new ArrayList<>();
        for  (Integer id: epic.getSubtaskIds()){
            subtask.add(subtasks.get(id));
            historyManager.add(subtasks.get(id));
        }
        return subtask;
    }

    public List<Task> getHistory(){
        return historyManager.getHistory();
    }

    public void updateEpicStatus(Epic epic){
        String status = "NEW";
        boolean isDone = true;
        boolean isProgress  = false;

        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            Subtask subtask = subtasks.get(epic.getSubtaskIds().get(i));
            if (subtask.getStatus().equals(Status.IN_PROGRESS)){
                status  = "IN_PROGRESS";
                isProgress = true;
                break;
            } else if (subtask.getStatus().equals(Status.NEW)) {
                isDone = false;
            } else if ((subtask.getStatus().equals(Status.DONE)) && (!isDone)) {
                status = "IN_PROGRESS";
                isProgress = true;
                break;
            }
        }
        if ((isDone) && (!isProgress)&&(!epic.getSubtaskIds().isEmpty())){
            epic.setStatus(Status.DONE);
        } else if ((!isDone) &&  (!isProgress)){
            epic.setStatus(Status.NEW);
        } else{
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}
