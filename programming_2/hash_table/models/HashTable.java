package programming_2.hash_table.models;

public class HashTable {
    private final int NUMBER_OF_BUCKETS = 30;
    private final LinkedList<String, String>[] TABLE_DATA = new LinkedList[NUMBER_OF_BUCKETS];
    private long numberOfItems;

    public HashTable() {
        for (int i = 0; i < NUMBER_OF_BUCKETS; i++) {
            TABLE_DATA[i] = new LinkedList<String, String>();
        }
        numberOfItems = 0;
    }

    private int getHashIndex(String object) {
        return Math.abs(object.hashCode()) % NUMBER_OF_BUCKETS;
    }

    public void put(String key, String value) {
        // if item already in list, we will update it
        Node<String, String> foundNode = getBucket(key).find(key);
        if (foundNode != null) {
            foundNode.value = value;
            return;
        }
        getBucket(key).add(key, value);
        numberOfItems++;
    }

    public String get(String key) {
        Node<String, String> foundNode = getBucket(key).find(key);
        return foundNode == null ? null : foundNode.value;
    }

    public void remove(String key) {
        boolean successfullyRemoved = getBucket(key).remove(key);
        if (successfullyRemoved)
            numberOfItems--;
    }

    public boolean containsKey(String key) {
        return getBucket(key).find(key) == null ? false : true;
    }

    public long size() {
        return numberOfItems;
    }

    private LinkedList<String, String> getBucket(String key) {
        return TABLE_DATA[getHashIndex(key)];
    }
}
