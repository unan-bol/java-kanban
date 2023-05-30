import model.Epic;
import model.SimpleTask;
import model.Subtask;
import service.Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        SimpleTask simpleTask1 = new SimpleTask("UntitledSimpleTask1", "one", 10, "NEW");
        manager.saveSimpleTask(simpleTask1);
        SimpleTask simpleTask2 = new SimpleTask("UntitledSimpleTask2", "two", 10, "NEW");
        manager.saveSimpleTask(simpleTask2);

        Epic epic1 = new Epic("UntitledEpic1", "one epic", 10, "NEW");
        manager.saveEpic(epic1);
        Subtask subtask1 =  new Subtask("Untitled", "one subtask", 10, "NEW",  epic1.getId());
        manager.saveSubtask(subtask1);
        Subtask subtask2 = new Subtask("Untitled", "two subtask", 10, "DONE", epic1.getId());
        manager.saveSubtask(subtask2);

        Epic epic2 = new Epic("UntitledEpic2", "two epic", 10, "NEW");
        manager.saveEpic(epic2);
        Subtask subtask3 =  new Subtask("Untitled", "three subtask", 10, "NEW", epic2.getId());
        manager.saveSubtask(subtask3);


        System.out.println(manager.getAllSimpleTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getAllEpics());

        manager.deleteById(5);

        System.out.println(manager.getAllSimpleTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getAllEpics());





    }
}
