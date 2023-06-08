import jdk.jshell.Snippet;
import model.Epic;
import model.SimpleTask;
import model.Subtask;
import model.Status;
import service.InMemoryHistoryManager;
import service.TaskManager;
import service.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        SimpleTask simpleTask1 = new SimpleTask("UntitledSimpleTask1", "one", 10, Status.NEW);
        inMemoryTaskManager.saveSimpleTask(simpleTask1);
        SimpleTask simpleTask2 = new SimpleTask("UntitledSimpleTask2", "two", 10, Status.NEW);
        inMemoryTaskManager.saveSimpleTask(simpleTask2);

        Epic epic1 = new Epic("UntitledEpic1", "one epic", 10, Status.NEW);
        inMemoryTaskManager.saveEpic(epic1);
        Subtask subtask1 =  new Subtask("Untitled", "one subtask", 10, Status.NEW,  epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask1);
        Subtask subtask2 = new Subtask("Untitled", "two subtask", 10, Status.DONE, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask2);

        Epic epic2 = new Epic("UntitledEpic2", "two epic", 10, Status.NEW);
        inMemoryTaskManager.saveEpic(epic2);
        Subtask subtask3 =  new Subtask("Untitled", "three subtask", 10, Status.NEW, epic2.getId());
        inMemoryTaskManager.saveSubtask(subtask3);


        System.out.println(inMemoryTaskManager.getAllSimpleTasks());
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println(inMemoryTaskManager.getAllSubtasks());
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println(inMemoryTaskManager.getHistory());






    }
}
