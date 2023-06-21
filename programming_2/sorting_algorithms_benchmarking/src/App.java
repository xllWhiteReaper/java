package programming_2.sorting_algorithms_benchmarking.src;

import java.util.Arrays;

import programming_2.sorting_algorithms_benchmarking.utils.ArraySorter;
import programming_2.sorting_algorithms_benchmarking.utils.NumberGenerator;
import programming_2.sorting_algorithms_benchmarking.utils.TimeManager;

/**
 * This class compares the performance of a normal sorting algorithm and a
 * custom-built algorithm that focuses on performance.
 * 
 * <p>
 * Performance measurements:
 * </p>
 * <ul>
 * <li>With 1000 integers:
 * <ul>
 * <li>Insertion sort: {@value 1.666} milliseconds.</li>
 * <li>Built-in sort: {@value 0} milliseconds.</li>
 * </ul>
 * </li>
 * <li>With 10000 integers:
 * <ul>
 * <li>Insertion sort: {@value 21} milliseconds.</li>
 * <li>Built-in sort: {@value 1.666} milliseconds.</li>
 * </ul>
 * </li>
 * <li>With 100000 integers:
 * <ul>
 * <li>Insertion sort: {@value 1726.666} milliseconds.</li>
 * <li>Built-in sort: {@value 8.333} milliseconds.</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @param ARRAY_SIZE the size of the array to sort
 * 
 * @author A-Cobra
 */
public class App {
    private static final int ARRAY_SIZE = 100000;

    public static void main(String[] args) {
        int[] firstArray = new int[ARRAY_SIZE];
        int[] secondArray = new int[ARRAY_SIZE];
        NumberGenerator.fillArrays(firstArray, secondArray);
        TimeManager[] timers = new TimeManager[2];

        // Normal Sorting
        timers[0] = new TimeManager("Insertion Sorting");
        ArraySorter.insertionSort(firstArray);
        timers[0].printElapsedTime();
        // Normal Sorting

        // Java Built-in Sorting
        timers[1] = new TimeManager("Java Built-in Sorting");
        Arrays.sort(secondArray);
        timers[1].printElapsedTime();
        // Java Built-in Sorting
    }
}