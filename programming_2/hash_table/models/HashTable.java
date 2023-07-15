package programming_2.hash_table.models;

public class HashTable {
    private final int TABLE_SIZE = 30;
    private final LinkedList<String>[] TABLE_DATA = new LinkedList[TABLE_SIZE];

    public HashTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            TABLE_DATA[i] = new LinkedList<String>();
        }
    }

    private int getHashIndex(String object) {
        return Math.abs(object.hashCode()) % TABLE_SIZE;
    }
}
