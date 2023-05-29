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
        Subtask subtask1 =  new Subtask("Untitled", "one subtask", 10, "NEW");
        manager.saveSubtask(subtask1, epic1.getId());
        Subtask subtask2 = new Subtask("Untitled", "two subtask", 10, "NEW");
        manager.saveSubtask(subtask2, epic1.getId());

        Epic epic2 = new Epic("UntitledEpic2", "two epic", 10, "NEW");
        manager.saveEpic(epic2);
        Subtask subtask3 =  new Subtask("Untitled", "three subtask", 10, "NEW");
        manager.saveSubtask(subtask3, epic2.getId());

        manager.updateEpic(new Epic("Untitled", "again", 10, "NEW"), epic1.getId());

        System.out.println(manager.getAllSimpleTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getSimpleTaskById(simpleTask2.getId()));
        System.out.println(manager.getEpicById(epic1.getId()));
        System.out.println(manager.getSubtaskById(subtask3.getId()));
        System.out.println(manager.getSubtaskByEpic(epic1));

        manager.deleteById(3);
        manager.deleteSubTask();

        System.out.println(manager.getAllSimpleTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getAllEpics());





    }
}
