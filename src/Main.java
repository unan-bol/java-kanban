import model.*;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        SimpleTask simpleTask1 = new SimpleTask("UntitledSimpleTask1", "one", 1, Status.NEW, Type.SIMPLETASK);
        inMemoryTaskManager.saveSimpleTask(simpleTask1);
        SimpleTask simpleTask2 = new SimpleTask("UntitledSimpleTask2", "two", 2, Status.NEW, Type.SIMPLETASK);
        inMemoryTaskManager.saveSimpleTask(simpleTask2);

        Epic epic1 = new Epic("UntitledEpic1", "one epic", 3, Status.NEW, Type.EPIC);
        inMemoryTaskManager.saveEpic(epic1);
        Subtask subtask1 =  new Subtask("Untitled", "one subtask", 4, Status.NEW, Type.SUBTASK, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask1);
        Subtask subtask2 = new Subtask("Untitled", "two subtask", 5, Status.DONE, Type.SUBTASK, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask2);
        Subtask subtask3 = new Subtask("Untitled", "three subtask", 6, Status.DONE, Type.SUBTASK, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask3);
        Subtask subtask4 = new Subtask("Untitled", "four subtask", 7, Status.DONE, Type.SUBTASK, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask4);

        Epic epic2 = new Epic("UntitledEpic2", "two epic", 8, Status.NEW, Type.EPIC);
        inMemoryTaskManager.saveEpic(epic2);



        System.out.println(inMemoryTaskManager.getAllSimpleTasks());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getAllSubtasks());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getAllSimpleTasks());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println("");

        inMemoryTaskManager.deleteById(4);
        System.out.println(inMemoryTaskManager.getAllSubtasks());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println("");
    }
}
