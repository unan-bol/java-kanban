package service;

import model.Task;
import model.Epic;
import model.SimpleTask;
import model.Subtask;
import java.util.ArrayList;

public interface TaskManager {
    // метод сохраняет простую задачу в список простых задач
    int saveSimpleTask(SimpleTask task);
    // метод сохраняет эпик в список эпиков
    int saveEpic(Epic epic);
    // метод сохраняет подзадачи в список подзадач
    int saveSubtask(Subtask subtask);
    // метод удаляет все простые задачи
    void deleteSimpleTasks();
    // метод удаляет все эпики вместе с подзадачами
    void deleteEpic();
    // метод удаляет все подзадачи и очищает список id подзадач в эпике
    void deleteSubTask();
    //метод удаляет задачу с указанным id
    void deleteById(int id);
    // метод обновляет простую задачу с указанным id
    void updateSimpleTask(SimpleTask simpleTask, int id);
    // метод обновляет подзадачу с указанным id
    void updateSubtask(Subtask subtask, int id);
    // метод обновляет эпик с указанным id
    void updateEpic(Epic epic, int id);
    // метод выводит все сохраненные простые задачи
    ArrayList<SimpleTask> getAllSimpleTasks();
    // метод выводит все сохраненные эпики
    ArrayList<Epic> getAllEpics();
    // метод выводит все сохраненные подзадачи
    ArrayList<Subtask> getAllSubtasks();
    // выводит сохраненную простую задачу с указанным id
    SimpleTask getSimpleTaskById(int id);
    // выводит сохраненную подзадачу с указанным id
    Subtask getSubtaskById(int id);
    // выводит сохраненный эпик с указанным id
    Epic getEpicById(int id);
    // выводит все подзадачи эпика
    ArrayList<Subtask> getSubtaskByEpic(Epic epic);



}
