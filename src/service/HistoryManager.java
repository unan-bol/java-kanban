package service;
import model.Task;

import java.util.List;
public interface HistoryManager {

    /**
     * Cохраняет просмотренную задачу в список истории.
     * @task задача.
     */
    void add(Task task);

    /**
     * Выводит историю задач.
    */
     List<Task> getHistory();

    /**
     * Удаление задач из просмотра.
     * @id id задачи
     */
    void remove(int id);

}
