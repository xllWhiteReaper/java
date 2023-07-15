package programming_2.hash_table.models;

import javax.xml.crypto.Data;

public class LinkedList<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;

    public void add(K key, V value) {
        Node<K, V> newNode = new Node<K, V>(key, value);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        System.out.println("Added item");
        printList();
    }

    public void remove(K key) {
        if (head == null) {
            return;
        }
        if (head.key.equals(key)) {
            head = head.next;
            return;
        }
        Node<K, V> current = head;
        while (current.next != null) {
            if (head.key.equals(key)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public void printList() {
        Node<K, V> current = head;
        while (current != null) {
            System.out.print(current.key + " " + current.value);
            current = current.next;
        }
        System.out.println();
    }

    public Node<K, V> find(K key) {
        System.out.println("FINDING " + key);
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            System.out.println("searching key " + key);
            System.out.println("current key " + currentNode.key);
            if (currentNode.key.equals(key)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}