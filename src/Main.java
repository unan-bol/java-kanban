import model.Epic;
import model.SimpleTask;
import model.Subtask;
import model.Status;
import service.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        SimpleTask simpleTask1 = new SimpleTask("UntitledSimpleTask1", "one", 10, Status.NEW);
        inMemoryTaskManager.saveSimpleTask(simpleTask1);
        SimpleTask simpleTask2 = new SimpleTask("UntitledSimpleTask2", "two", 10, Status.NEW);
        inMemoryTaskManager.saveSimpleTask(simpleTask2);
        SimpleTask simpleTask3 = new SimpleTask("UntitledSimpleTask3", "three", 10, Status.NEW);
        inMemoryTaskManager.saveSimpleTask(simpleTask3);

        Epic epic1 = new Epic("UntitledEpic1", "one epic", 10, Status.NEW);
        inMemoryTaskManager.saveEpic(epic1);
        Subtask subtask1 =  new Subtask("Untitled", "one subtask", 10, Status.NEW,  epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask1);
        Subtask subtask2 = new Subtask("Untitled", "two subtask", 10, Status.DONE, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask2);
        Subtask subtask3 = new Subtask("Untitled", "three subtask", 10, Status.DONE, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask3);
        Subtask subtask4 = new Subtask("Untitled", "four subtask", 10, Status.DONE, epic1.getId());
        inMemoryTaskManager.saveSubtask(subtask4);

        Epic epic2 = new Epic("UntitledEpic2", "two epic", 10, Status.NEW);
        inMemoryTaskManager.saveEpic(epic2);
        Subtask subtask5 =  new Subtask("Untitled", "five subtask", 10, Status.NEW, epic2.getId());
        inMemoryTaskManager.saveSubtask(subtask5);
        Subtask subtask6 =  new Subtask("Untitled", "six subtask", 10, Status.NEW, epic2.getId());
        inMemoryTaskManager.saveSubtask(subtask6);
        Subtask subtask7 =  new Subtask("Untitled", "seven subtask", 10, Status.NEW, epic2.getId());
        inMemoryTaskManager.saveSubtask(subtask7);


        /*System.out.println(inMemoryTaskManager.getAllSimpleTasks());
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
        System.out.println("");*/

        inMemoryTaskManager.deleteById(4);
        System.out.println(inMemoryTaskManager.getAllSubtasks());
        System.out.println("");
        System.out.println(inMemoryTaskManager.getAllEpics());
    }
}
