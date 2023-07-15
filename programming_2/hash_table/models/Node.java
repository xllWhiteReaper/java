package programming_2.hash_table.models;

public class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }
}