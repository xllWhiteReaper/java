package programming_2.hash_table.src;

import java.util.HashMap;
import java.util.Map;

import programming_2.hash_table.models.HashTable;

public class App {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "value");
        System.out.println("GETTING");
        System.out.println(map.get("key"));
        System.out.println("BUILT BY AIZEN");
        HashTable hashTable = new HashTable();
        hashTable.put("key1", "value1");
        hashTable.put("key1", "updatedValue1");
        System.out.println("contains key1 before removal " + hashTable.containsKey("key1"));
        hashTable.remove("key1");
        System.out.println("contains key1 after removal " + hashTable.containsKey("key1"));
        System.out.println("GETTING");
        System.out.println(hashTable.get("key1"));
        System.out.println("hashTable.get(key2)");
        System.out.println(hashTable.get("key2"));
        System.out.println("Works");
    }
}
