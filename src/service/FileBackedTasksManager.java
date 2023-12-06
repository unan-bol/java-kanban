package service;
import exception.ManagerSaveException;
import model.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager{
    File file;
    public FileBackedTasksManager(File file){
        this.file = file;
    }
    /**
     * Сохраняет текущее состояние менеджера в указанный файл.
     */
    public void save(){
        try(FileWriter writer = new FileWriter(file)){
            writer.write("name,details,id,status,type,epicId" + "\n");
            for(Task task: getAllTasks()) {
                writer.write(toStringTask(task) + "\n");
            }
            writer.write("\n" + historyToString(historyManager));
        } catch (IOException exception){
            try {
                throw new ManagerSaveException(exception.getMessage());
            } catch (ManagerSaveException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Преобразует задачу в строку.
     * @task задача.
     */
    public String toStringTask(Task task) {
        return task.taskToString();
    }
    /**
     * Преобразует строку в задачу.
     * @value строка.
     */
    public Task fromString(String value) {
        String[] taskArray = value.split(",");
        String name = taskArray[0];
        String details = taskArray[1];
        int id = Integer.parseInt(taskArray[2]);
        Status status = Status.valueOf(taskArray[3]);
        Type type = Type.valueOf(taskArray[4]);
        Task task = null;
        int epicId = 0;
        if (type.equals(Type.SUBTASK)) {
            epicId = Integer.parseInt(taskArray[5]);
        }
        if (type.equals(Type.SIMPLETASK)) {
            task = new SimpleTask(name, details, id, status, type);
        } else if ((type.equals(Type.EPIC))){
            task = new Epic(name, details, id, status, type);
        } else if(type.equals(Type.SUBTASK)){
                task = new Subtask(name, details, id, status, type, epicId);
        }
        return task;
    }
    /**
     * Сохраняет менеджер истории в строку.
     * @manager менеджер истории.
     */
    static String historyToString(HistoryManager manager){
        String history;
        List<String> tasks = new ArrayList<>();
        for (Task task: manager.getHistory()){
            tasks.add(Integer.toString(task.getId()));
        }
        history = String.join(",",tasks);
        return history;
    }
    /**
     * Восстанавливает менеджер истории из строки.
     * @value строка истории
     */
    static List<Integer> historyFromString(String value){
        String[] split = value.split(",");
        List<Integer> newList = new ArrayList<>();
        for(String str: split){
            newList.add(Integer.parseInt(str));
        }
        return newList;
    }

    /**
     * Восстанавливает менеджер истории при запуске программы.
     * @file файл истории
     */
    static FileBackedTasksManager loadFromFile(File file){
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String text = bufferedReader.readLine();
            while(bufferedReader.ready()){
                String line = bufferedReader.readLine();
                if(line.isBlank()){
                    break;
                } else{
                    Task task = fileBackedTasksManager.fromString(line);
                    if(task.getType().equals(Type.SIMPLETASK)) {
                        fileBackedTasksManager.simpleTasks.put(task.getId(), (SimpleTask) task);
                    } else if(task.getType().equals(Type.EPIC)) {
                            fileBackedTasksManager.epics.put(task.getId(), (Epic) task);

                    }else if(task.getType().equals(Type.SUBTASK)){
                            fileBackedTasksManager.subtasks.put(task.getId(), (Subtask) task);
                    }
                }
            }
            String history = bufferedReader.readLine();
            for(Integer id: historyFromString(history)){
                if(fileBackedTasksManager.simpleTasks.containsKey(id)){
                    fileBackedTasksManager.historyManager.add(fileBackedTasksManager.simpleTasks.get(id));
                }
                if(fileBackedTasksManager.subtasks.containsKey(id)){
                    fileBackedTasksManager.historyManager.add(fileBackedTasksManager.subtasks.get(id));
                }
                if(fileBackedTasksManager.epics.containsKey(id)){
                    fileBackedTasksManager.historyManager.add(fileBackedTasksManager.epics.get(id));
                }
            }
        } catch (IOException exception){
            try {
                throw new ManagerSaveException(exception.getMessage());
            } catch (ManagerSaveException e) {
                throw new RuntimeException(e);
            }
        }
        return fileBackedTasksManager;
    }

    public static void main(String[] args){
        File file = new File("src/service/file.csv");
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        SimpleTask simpleTask1 = new SimpleTask("UntitledSimpleTask1", "one", 1, Status.NEW, Type.SIMPLETASK);
        fileBackedTasksManager.saveSimpleTask(simpleTask1);
        SimpleTask simpleTask2 = new SimpleTask("UntitledSimpleTask2", "two", 2, Status.NEW, Type.SIMPLETASK);
        fileBackedTasksManager.saveSimpleTask(simpleTask2);

        Epic epic1 = new Epic("UntitledEpic1", "one epic", 3, Status.NEW, Type.EPIC);
        fileBackedTasksManager.saveEpic(epic1);
        Subtask subtask1 =  new Subtask("Untitled", "one subtask", 4, Status.NEW, Type.SUBTASK, epic1.getId());
        fileBackedTasksManager.saveSubtask(subtask1);
        Subtask subtask2 = new Subtask("Untitled", "two subtask", 5, Status.DONE, Type.SUBTASK, epic1.getId());
        fileBackedTasksManager.saveSubtask(subtask2);
        Subtask subtask3 = new Subtask("Untitled", "three subtask", 6, Status.DONE, Type.SUBTASK, epic1.getId());
        fileBackedTasksManager.saveSubtask(subtask3);
        Subtask subtask4 = new Subtask("Untitled", "four subtask", 7, Status.DONE, Type.SUBTASK, epic1.getId());
        fileBackedTasksManager.saveSubtask(subtask4);

        Epic epic2 = new Epic("UntitledEpic2", "two epic", 8, Status.NEW, Type.EPIC);
        fileBackedTasksManager.saveEpic(epic2);
        fileBackedTasksManager.getSubtaskById(6);
        fileBackedTasksManager.getAllSimpleTasks();

        FileBackedTasksManager fileBackedTasksManager2 = loadFromFile(file);
        Epic epic3 = new Epic("UntitledEpic3", "three epic", 9, Status.NEW, Type.EPIC);
        fileBackedTasksManager2.saveEpic(epic3);
    }
    public List<Task> getAllTasks(){
        List<Task> allTasks = new ArrayList<>();
        for(Task task:  simpleTasks.values()){
            allTasks.add(task);
        }
        for(Task task: subtasks.values()){
            allTasks.add(task);
        }
        for(Task task:  epics.values()){
            allTasks.add(task);
        }
        return allTasks;
    }

    @Override
    public int saveSimpleTask(SimpleTask task){
        int id = super.saveSimpleTask(task);
        save();
        return id;
    }

    @Override
    public int saveEpic(Epic epic){
        int id = super.saveEpic(epic);
        save();
        return id;
    }
    @Override
    public int saveSubtask(Subtask subtask) {
        int id = super.saveSubtask(subtask);
        save();
        return id;
    }


    @Override
    public void deleteSimpleTasks(){
        super.deleteSimpleTasks();
        save();
    }

    @Override
    public void deleteEpic(){
        super.deleteEpic();
        save();
    }

    @Override
    public void deleteSubTask(){
        super.deleteSubTask();
        save();
    }

    @Override
    public void deleteById(int id){
        super.deleteById(id);
        save();
    }

    @Override
    public void updateSimpleTask(SimpleTask simpleTask, int id){
        super.updateSimpleTask(simpleTask, id);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask, int id){
        super.updateSubtask(subtask, id);
        save();
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        super.updateEpic(epic, id);
        save();
    }

    @Override
    public void updateEpicStatus(Epic epic){
        super.updateEpicStatus(epic);
        save();
    }

    @Override
    public SimpleTask getSimpleTaskById(int id){
        SimpleTask simpletask = super.getSimpleTaskById(id);
        save();
        return simpletask;
    }

    @Override
    public Subtask getSubtaskById(int id){
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public Epic getEpicById(int id){
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public List<Subtask> getSubtaskByEpic(Epic epic){
        List<Subtask> subtasks = super.getSubtaskByEpic(epic);
        save();
        return subtasks;
    }
    @Override
    public List<SimpleTask> getAllSimpleTasks(){
        List<SimpleTask> tasks = super.getAllSimpleTasks();
        save();
        return tasks;
    }

    @Override
    public List<Epic> getAllEpics(){
        List<Epic> tasks = super.getAllEpics();
        save();
        return tasks;
    }

    @Override
    public List<Subtask> getAllSubtasks(){
        List<Subtask> tasks = super.getAllSubtasks();
        save();
        return tasks;
    }
}
