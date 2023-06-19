package programming_2.sorting_algorithms_benchmarking.utils;

import java.util.Random;

public class NumberGenerator {
    private final static int BOUND = 20;
    private final static Random RANDOM = new Random();

    /**
     * We suppose that all the arrays have the same size
     * 
     * @param arrays
     * @param array2
     */
    public static void fillArrays(int[]... arrays) {
        try {
            final int ARRAY_SIZE = arrays[0].length;
            for (int i = 0; i < ARRAY_SIZE; i++) {
                final int randomNumber = getRandomNumber();
                for (int[] array : arrays) {
                    array[i] = randomNumber;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The arrays have to have the same number of items");
        } catch (Exception e) {
            System.out.println("The arrays list of lists must have at least one array");
        }
    }

    private static int getRandomNumber() {
        return RANDOM.nextInt(BOUND + 1);
    }
}
