package programming_2.sorting_algorithms_benchmarking.src;

import java.util.Arrays;

import programming_2.sorting_algorithms_benchmarking.utils.ArraySorter;
import programming_2.sorting_algorithms_benchmarking.utils.NumberGenerator;
import programming_2.sorting_algorithms_benchmarking.utils.TimeManager;

/**
 * @author A-Cobra
 */
public class App {
    private static final int ARRAY_SIZE = 30000;

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