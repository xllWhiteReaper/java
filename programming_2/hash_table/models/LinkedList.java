package programming_2.hash_table.models;

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
    }

    public boolean remove(K key) {
        if (head == null) {
            return false;
        }
        if (head.key.equals(key)) {
            head = head.next;
            return true;
        }
        Node<K, V> current = head;
        while (current.next != null) {
            if (head.key.equals(key)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
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
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}