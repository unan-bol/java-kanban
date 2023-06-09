package service;
import model.Task;
import java.util.List;
public interface HistoryManager {

    //сохраняет просмотренную задачу в список истории
    void add(Task task);

    // выводит историю задач (последние 10 просмотренных задач)
    List<Task> getHistory();

}
