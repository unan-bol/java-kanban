package service;

import model.Task;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> head;
    private Node<Task> tail;
    private HashMap<Integer, Node> customLinkedList = new HashMap<>();

    @Override
    public void add(Task task) {
        int id = task.getId();
        remove(id);
        linkLast(task);
        customLinkedList.put(id, tail);
    }

    @Override
    public List<Task> getHistory() {
       return getTasks();
    }

    @Override
    public void remove(int id) {
        Node node = customLinkedList.get(id);
        if (node == null){
            return;
        }
        customLinkedList.remove(id);
        removeNode(node);
    }

    private void linkLast(Task task) {
        Node oldTail = tail;
        Node node = new Node(task, null, oldTail);
        tail = node;
        if (head == null) {
            head = node;
        } else {
            oldTail.next = node;
        }
    }

    private List<Task> getTasks() {
        List<Task> list = new ArrayList<>();
        Node node = head;
        while (node != null) {
            list.add(node.data);
            node = node.next;
        }
        return list;
    }

    private void removeNode(Node node) {
        Node<Task> oldHead = node.prev;
        Node<Task> oldTail = node.next;
        if (oldHead == null) {
            head = oldTail;
        } else {
            oldHead.next = oldTail;
        }
        if (oldTail == null) {
            tail = oldHead;
        } else {
            oldTail.prev = oldHead;
        }
    }

    public static class Node<T> {

        public Task data;
        public Node<Task> next;
        public Node<Task> prev;


        public Node(Task data, Node<Task> next, Node<Task> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
