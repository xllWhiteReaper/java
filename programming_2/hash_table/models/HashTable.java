package programming_2.hash_table.models;

public class HashTable {
    private final int TABLE_SIZE = 30;
    private final LinkedList<String, String>[] TABLE_DATA = new LinkedList[TABLE_SIZE];

    public HashTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            TABLE_DATA[i] = new LinkedList<String, String>();
        }
    }

    private int getHashIndex(String object) {
        System.out.println("Index " + Math.abs(object.hashCode()) % TABLE_SIZE);
        return Math.abs(object.hashCode()) % TABLE_SIZE;
    }

    public void put(String key, String value) {
        // if item already in list, we will update it
        Node<String, String> foundNode = TABLE_DATA[getHashIndex(key)].find(key);
        if (foundNode != null) {
            foundNode.value = value;
            return;
        }
        TABLE_DATA[getHashIndex(key)].add(key, value);
    }

    public String get(String key) {
        Node<String, String> foundNode = TABLE_DATA[getHashIndex(key)].find(key);
        return foundNode == null ? null : foundNode.value;
    }
}
