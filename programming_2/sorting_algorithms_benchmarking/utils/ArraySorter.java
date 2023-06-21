package programming_2.sorting_algorithms_benchmarking.utils;

public class ArraySorter {
    /**
     * @param integerArray array to be sorted
     */
    public static void insertionSort(int[] integerArray) {
        // Sort the array A into increasing order.
        int itemsSorted; // Number of items that have been sorted so far.
        for (itemsSorted = 1; itemsSorted < integerArray.length; itemsSorted++) {
            // Assume that items A[0], A[1], ... A[itemsSorted-1]
            // have already been sorted. Insert A[itemsSorted]
            // into the sorted part of the list.
            int temp = integerArray[itemsSorted]; // The item to be inserted.
            int loc = itemsSorted - 1; // Start at end of list.
            while (loc >= 0 && integerArray[loc] > temp) {
                integerArray[loc + 1] = integerArray[loc]; // Bump item from A[loc] up to loc+1.
                loc = loc - 1; // Go on to next location.
            }
            integerArray[loc + 1] = temp; // Put temp in last vacated space.
        }
    }
}
