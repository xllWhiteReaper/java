package programming_2.hash_table.src;

import programming_2.hash_table.models.HashTable;

public class App {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        System.out.println("Size of map at creation: " + hashTable.size());

        hashTable.put("key1", "value1");
        hashTable.put("key1", "updatedValue1");

        String key1 = "key1";

        System.out.println("Getting key for key: " + key1 + ": " + hashTable.get(key1));
        System.out.println("contains key1 before removal " + hashTable.containsKey("key1"));
        System.out.println("Size of map before removal: " + hashTable.size());

        hashTable.remove("key1");
        hashTable.remove("key4");

        System.out.println("contains key1 after removal " + hashTable.containsKey("key1"));
        System.out.println("Size of map after removal: " + hashTable.size());

        System.out.println(hashTable.get(key1));
        System.out.println(hashTable.get("key2"));
    }
}
