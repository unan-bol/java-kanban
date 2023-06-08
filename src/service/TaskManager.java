package service;

import model.Task;
import model.Epic;
import model.SimpleTask;
import model.Subtask;
import java.util.ArrayList;

public interface TaskManager {
    int saveSimpleTask(SimpleTask task);
    int saveEpic(Epic epic);
    int saveSubtask(Subtask subtask);
    void deleteSimpleTasks();
    void deleteEpic();
    void deleteSubTask();
    void deleteById(int id);
    void updateSimpleTask(SimpleTask simpleTask, int id);
    void updateSubtask(Subtask subtask, int id);
    void updateEpic(Epic epic, int id);
    ArrayList<SimpleTask> getAllSimpleTasks();
    ArrayList<Epic> getAllEpics();
    ArrayList<Subtask> getAllSubtasks();
    SimpleTask getSimpleTaskById(int id);
    Subtask getSubtaskById(int id);
    Epic getEpicById(int id);
    ArrayList<Subtask> getSubtaskByEpic(Epic epic);



}
