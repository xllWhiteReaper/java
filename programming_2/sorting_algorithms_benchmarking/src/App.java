package programming_2.sorting_algorithms_benchmarking.src;

import programming_2.sorting_algorithms_benchmarking.utils.ArraySorter;
import programming_2.sorting_algorithms_benchmarking.utils.NumberGenerator;

/**
 * @author A-Cobra
 */
public class App {
    private static final int ARRAY_SIZE = 20;

    public static void main(String[] args) {
        int[] firstArray = new int[ARRAY_SIZE];
        int[] secondArray = new int[ARRAY_SIZE];
        NumberGenerator.fillArrays(firstArray, secondArray);
        ArraySorter.insertionSort(firstArray);
    }
}