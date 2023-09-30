package service;

import model.Task;
import model.Epic;
import model.SimpleTask;
import model.Subtask;
import java.util.List;

public interface TaskManager {
    /**
     * Метод сохраняет простую задачу в список простых задач.
     * @task задача
     */
    int saveSimpleTask(SimpleTask task);
    /**
     * Метод сохраняет эпик в список эпиков.
     * @epic эпик
     */
    int saveEpic(Epic epic);
    /**
     * Метод сохраняет подзадачи в список подзадач.
     * @subtask подзадача
     */
    int saveSubtask(Subtask subtask);
    /**
     * Метод удаляет все простые задачи.
     */
    void deleteSimpleTasks();
    /**
     * Метод удаляет все эпики вместе с подзадачами.
     */
    void deleteEpic();
    /**
     * Метод удаляет все подзадачи и очищает список id подзадач в эпике.
     */
    void deleteSubTask();
    /**
     * Метод удаляет задачу с указанным id.
     * @id id задачи
     */
    void deleteById(int id);
    /**
     * Метод обновляет простую задачу с указанным id.
     * @simpletask задача
     * @id id задачи
     */
    void updateSimpleTask(SimpleTask simpleTask, int id);
    /**
     * Метод обновляет подзадачу с указанным id.
     * @subtask подзадача
     * @id id подзадачи
     */
    void updateSubtask(Subtask subtask, int id);
    /**
     * Метод обновляет эпик с указанным id.
     * @epic эпик
     * @id id эпика
     */
    void updateEpic(Epic epic, int id);
    /**
     * Метод выводит все сохраненные простые задачи.
     */
    List<SimpleTask> getAllSimpleTasks();
    /**
     * Метод выводит все сохраненные эпики.
     */
    List<Epic> getAllEpics();
    /**
     * Метод выводит все сохраненные подзадачи.
     */
    List<Subtask> getAllSubtasks();
    /**
     * Метод выводит сохраненную простую задачу с указанным id.
     * @id id задачи
     */
    SimpleTask getSimpleTaskById(int id);
    /**
     * Метод выводит сохраненную подзадачу с указанным id.
     * @id id подзадачи
     */
    Subtask getSubtaskById(int id);
    /**
     * Метод выводит сохраненный эпик с указанным id.
     * @id id эпика
     */
    Epic getEpicById(int id);
    /**
     * Метод выводит все подзадачи эпика.
     * @epic эпик
     */
    List<Subtask> getSubtaskByEpic(Epic epic);



}
